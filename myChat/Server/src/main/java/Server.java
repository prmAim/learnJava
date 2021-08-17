import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс <сервер>
 */
public class Server {
  private ServerSocket server;
  private Socket socket;         // Сокет для подключения
  private final int PORT = 8189; // Порт для подклчюения

  private List<ClientHandler> clients;
  private AuthService authService;

  public Server() {
    clients = new CopyOnWriteArrayList<>();  // Список клиентов
    authService = new SimpleAuthService();   // Аутентивикация клиентов
    try {
      server = new ServerSocket(PORT);    // Открываем порт на сервере
      System.out.println("LOG: Server started!");

      while (true) {
        socket = server.accept();       // Слушаем порт. БЛОКИРУЮЩАЯ операция.
        System.out.println("LOG: Client connected! Сокет " + socket.getRemoteSocketAddress());
        new ClientHandler(socket, this);    // Создаем объект <работа с клиентом>. Запускаем логику ратобы с клиентом.
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        server.close();             // Закрываем соединение.
        System.out.println("LOG: Server close!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Широковещательное сообщение всем клиентам
   */
  public void broadcastMsg(ClientHandler sender, String msg) {
    String message = String.format("[ %s ]: %s", sender.getNickname(), msg);
    for (ClientHandler c : clients) {
      c.sendMsg(message);
    }
  }

  /**
   * Реализовать личные сообщения, если клиент пишет «/w nick3 Привет, как дела?», то только клиенту с ником nick3 и отправителю должно прийти сообщение «Привет, как дела? »
   */
  public void sendMsgToClient(ClientHandler sender, String toClient, String msg) {
    String message = String.format("[ %s ]: %s", sender.getNickname(), msg);
    for (ClientHandler c : clients) {
      if (c.getNickname().equals(toClient)){
        sender.sendMsg(message);
        c.sendMsg(message);
        return;
      }
    }
    sender.sendMsg(String.format("[ %s ]: В сети нет такого псевдонима %s", sender.getNickname(), toClient));
  }

  /**
   * Добавление клиента в рассылку
   */
  public void subscribe(ClientHandler clientHandler) {
    clients.add(clientHandler);
  }

  /**
   * Удаление клиента из рассылки
   */
  public void unsubscribe(ClientHandler clientHandler) {
    clients.remove(clientHandler);
  }

  /**
   * Получить ссылку на объект авторизации
   */
  public AuthService getAuthService() {
    return authService;
  }
}
