import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
  @FXML
  private TextArea textArea;
  @FXML
  private TextField textField;
  @FXML
  private TextField loginField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private HBox authPanel;
  @FXML
  private HBox sendMsgPanel;

  private Socket socket;
  private final int PORT = 8189;                  // Порт сервера
  private final String IP_ADDRESS = "localhost";  // IP-адрес сервера
  private DataInputStream in;
  private DataOutputStream out;

  private boolean authenticated;
  private String nickname;
  private Stage stage;

  /**
   * Режим работы клиента Авторизован/НЕ автаризован
   */
  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
    authPanel.setVisible(!authenticated);     // панель Авторизации authPanel = не показывать
    authPanel.setManaged(!authenticated);     // панель Авторизации authPanel = скрыть. Не учитывать в размерах.
    sendMsgPanel.setVisible(authenticated);   // панель передачи сообщения sendMsgPanel = показать
    sendMsgPanel.setManaged(authenticated);

    if (!authenticated) {
      nickname = "";
    }
    setTitle(nickname);           // установить заголовок Окна
    textArea.clear();             // Очистить сообщения в чате
  }

  /**
   * После всей загрузки всех переменных класса Controller и проверки этих переменных, запускается этот метод
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Log: Client start!");
    Platform.runLater(() -> {
      stage = (Stage) textField.getScene().getWindow();
      stage.setOnCloseRequest(new EventHandler<WindowEvent>() {     // событие на закрытие экрана
        @Override
        public void handle(WindowEvent event) {
          System.out.println("Log: Bye!");
          if (socket != null && !socket.isClosed()) {         // проверка на открытие сокета. Если открыт, закрываем.
            try {
              out.writeUTF("/end");
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
    });
    setAuthenticated(false);
  }

  private void connect() {
    try {
      socket = new Socket(IP_ADDRESS, PORT);
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());

      new Thread(() -> {
        try {
          // цикл аутентификации
          while (true) {
            String str = in.readUTF();                // Блокирующая операция. Чтение из входного потока (от сервера).
            if (str.startsWith("/")) {
              if (str.equals("/end")) {
                break;
              }
              if (str.startsWith("/authOK")) {
                nickname = str.split("\\s")[1];
                setAuthenticated(true);               // Аутентификация одобрена. Переход в режим отправки сообщений в чат.
                break;
              }
            } else {
              textArea.appendText(str + "\n");
            }
          }
          // цикл работы
          while (authenticated) {
            String str = in.readUTF();          // Блокирующая операция. Чтение из входного потока (от сервера).

            if (str.equals("/end")) {
              break;
            }
            textArea.appendText(str + "\n");  // Вывод данных в поле сообщений
          }

        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          System.out.println("LOG: disconnected");
          setAuthenticated(false);            // закрытие авторизации
          try {
            socket.close();
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
   * Обработка формы отправки сообщений
   */
  @FXML
  public void sendMsg(ActionEvent actionEvent) {
    try {
      out.writeUTF(textField.getText());    // Отправить сообщение в исходящий поток (в сервер)
      textField.clear();
      textField.requestFocus();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Отправка авторизация.
   */
  public void sendAuth(ActionEvent actionEvent) {
    if (socket == null || socket.isClosed()) {    //Если не установлена связь, соединиться с сервером.
      connect();
    }

    String login = loginField.getText().trim();
    String password = passwordField.getText().trim();     // Убираем пробелы
    String msg = String.format("/auth %s %s", login, password);

    try {
      out.writeUTF(msg);          // Отправка сообщения на сервер
      passwordField.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Установить название заголовка в окне Чата
   */
  private void setTitle(String nickname) {
    Platform.runLater(() -> {
      if (!nickname.equals("")) {
        stage.setTitle(String.format("Home Chat[ %s ]", nickname));
      } else {
        stage.setTitle("Home Chat");
      }
    });
  }

  /**
   * Метод обработки событий для закрытия приложения
   */
  @FXML
  public void clickClose() {
    Platform.runLater(() -> {
      if (socket != null && !socket.isClosed()) {         // проверка на открытие сокета. Если открыт, закрываем.
        try {
          out.writeUTF("/end");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Log: close window!");
      stage.close();
    });
    setAuthenticated(false);
  }


}
