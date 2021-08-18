import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Класс <Логика работы с клиентом>
 */
public class ClientHandler {
  Socket socket;
  Server server;
  DataInputStream in;
  DataOutputStream out;

  private boolean isAuthenticated;
  private String nickname;
  private String login;

  public ClientHandler(Socket socket, Server server) {
    try {
      this.socket = socket;
      this.server = server;

      in = new DataInputStream(socket.getInputStream());      // Поток входящий из сокета. Данные от клиента.
      out = new DataOutputStream(socket.getOutputStream());   // Исходящий поток. Отправка данных клиенту.

      new Thread(() -> {
        try {
          // цикл аутентификации
          while (true) {
            String str = in.readUTF();                  // БЛОКИРУЮЩАЯ операция. Ждем данных от клиента.

            if (str.equals("/end")) {
              sendMsg("/end");
              System.out.println("Client disconnected");
              break;
            }
            if (str.startsWith("/auth ")) {
              String[] token = str.split("\\s+");
              nickname = server.getAuthService()          // Проверка на аутентификацию
                      .getNicknameByLoginAndPassword(token[1], token[2]);
              login = token[1];
              if (nickname != null) {
                if (!server.isLoginAuthenticated(login)) {
                  sendMsg("/authOK " + nickname);        // Отправка ссобщение клиенту об успешной авторизации
                  server.subscribe(this);     // Добавление клиента в список рассылки
                  isAuthenticated = true;                // Флаг аутентификации
                  System.out.printf("LOG: Client [%s] authecated!\n", nickname);
                  break;
                } else {
                  sendMsg("С этим логином уже вошли");
                }
              } else {
                sendMsg("Неверный логин / пароль");
              }
            }

            if (str.startsWith("/reg ")) {
              String[] token = str.split("\\s+");
              if (token.length < 4) {
                continue;
              }

              boolean regOk = server.getAuthService().
                      registration(token[1], token[2], token[3]);
              if (regOk) {
                sendMsg("/regOK");
              } else {
                sendMsg("/regError");
              }
            }
          }
          // цикл работы
          while (isAuthenticated) {
            String str = in.readUTF();

            if (str.startsWith("/")) {
              if (str.equals("/end")) {
                sendMsg("/end");
                System.out.printf("LOG: Client %s disconnected!\n", nickname);
                break;
              }

              if (str.startsWith("/w")) {
                String[] token = str.split("\\s+", 3);
                if (token.length < 3) {
                  continue;
                }
                server.privateMsg(this, token[1], token[2]);
              }
            } else {
              server.broadcastMsg(this, str);      // Широковещательная рассылка
            }
          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          server.unsubscribe(this);           // Удаляем клиента из рассылки
          System.out.printf("LOG: Client [%s] unsubscribe!\n", nickname);
          try {
            System.out.println("LOG: socket " + socket.getRemoteSocketAddress() + " close!");
            socket.close();                             // Закрываем сокет с клиентом
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Отправка сообщения
   */
  public void sendMsg(String msg) {
    try {
      out.writeUTF(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Получить псевдоним клиента
   */
  public String getNickname() {
    return nickname;
  }

  public String getLogin() {
    return login;
  }
}
