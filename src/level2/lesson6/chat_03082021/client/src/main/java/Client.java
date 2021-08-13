package level2.lesson6.chat_03082021.client.src.main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс для приложения <Клиент>
 */
public class Client {
    private static Socket socket;
  private static final int PORT_SERV = 8189;                 // Порт на Сервере.
  private static final String IP_ADRESS_SERV = "localhost";  // IP-адрес сервера

  public static void main(String[] args) {
    try {
      // Включение сервера
      socket = new Socket(IP_ADRESS_SERV, PORT_SERV);
      System.out.println("LOG: Client connected!");

      // Входящий Поток для получения сообщений от Сервера
      Scanner sc = new Scanner(socket.getInputStream());
      // Исходящий поток для отправки данных к Серверу через сокет
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // autoFlush - отправить сразу же
      // Входящий поток для получение данных с клвавиатуры
      Scanner sc2 = new Scanner(System.in);

      new Thread(() -> {    // Запустили параллельный поток
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
      }).start();

      while (true) {
        String str = sc.nextLine();     // Операция БЛОКИРУЮЩАЯ. получение данных от Сервера

        if (str.equals("/end")) {
          out.println("/end");
          System.out.println("LOG: Server disconnected");
          break;
        }
        System.out.println("Input data from Server: " + str);
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
    }
  }
}
