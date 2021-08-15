package level2.lesson6.chat_03082021.server.src.main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для приложения <Сервера>
 */
public class Server {
  private static ServerSocket server;
  private static Socket socket;
  private static final int PORT = 8189;                 // Входящий порт, которые слушает приложение.

  public static void main(String[] args) {
    try {
      // Включение сервера
      server = new ServerSocket(PORT);
      System.out.println("LOG: Server started!");

      //Поднятие сокета на сервера. Слушает входящий порт.
      socket = server.accept();
      System.out.println("LOG: Client connected");
      // Входящий Поток для получения сообщений от клиента
      Scanner sc = new Scanner(socket.getInputStream());
      // Исходящий поток для отправки данных в сокет
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // autoFlush - отправить сразу же
      // Входящий поток для получение данных с клвавиатуры
      Scanner sc2 = new Scanner(System.in);

      Thread threadReader = new Thread(() -> {    // Запустили параллельный поток
        while (true) {
          String inStr = sc2.nextLine();    // Операция БЛОКИРУЮЩАЯ. получение данных из консоли
          if (inStr.equals("/end")) {
            out.println("Buy!");
            out.println("/end");
            System.out.println("LOG: Input from console closed!");
            break;
          }
          out.println(inStr);
        }
      });
      threadReader.setDaemon(true);  // поток становиться дочерним. То есть если закроется поток main, то этот поток тоже закроется.
      threadReader.start();

      while (true) {
        String str = sc.nextLine();     // Операция БЛОКИРУЮЩАЯ. получение данных от клиента

        if (str.equals("/end")) {
          out.println("/end");
          System.out.println("LOG: Client disconnected");
          break;
        }
        System.out.println("Input data from client: " + str);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        System.out.println("LOG: Socket close!");
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        System.out.println("LOG: Server close!");
        server.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
