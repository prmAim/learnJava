import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

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

  private final int TIMEOUT_DISCONNECT = 120000;

  public ClientHandler(Socket socket, Server server) {
    try {
      this.socket = socket;
      this.server = server;

      in = new DataInputStream(socket.getInputStream());      // Поток входящий из сокета. Данные от клиента.
      out = new DataOutputStream(socket.getOutputStream());   // Исходящий поток. Отправка данных клиенту.

      new Thread(() -> {
        try {
          socket.setSoTimeout(TIMEOUT_DISCONNECT);
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
                  socket.setSoTimeout(0);
//                  sendMsg(SQLHandler.getMessageForNickname(nickname));    // Отправка истории из БД
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

              if (str.startsWith("/chknick")) {
                String[] token = str.split("\\s+", 2);
                if (token.length < 2) {
                  continue;
                }
                if (token[1].contains(" ")) {
                  sendMsg("Ник не может содержать пробелов");
                  continue;
                }
                if (server.getAuthService().changeNick(this.nickname, token[1])) {
                  sendMsg("/yournickis " + token[1]);
                  sendMsg("Ваш ник изменен на " + token[1]);
                  this.nickname = token[1];
                  server.broadcastClientList();
                } else {
                  sendMsg("Не удалось изменить ник. Ник " + token[1] + " уже существует");
                }
              }
            } else {
              server.broadcastMsg(this, str);      // Широковещательная рассылка
            }
          }
        } catch (SocketTimeoutException e){
          sendMsg("/end");
          System.out.printf("LOG: Client unknown disconnected with Timeout %d\n", TIMEOUT_DISCONNECT);
        }
        catch (IOException e) {
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
