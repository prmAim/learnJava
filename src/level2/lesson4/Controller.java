package level2.lesson4;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
  @FXML
  private TextArea textArea;
  @FXML
  private TextField textField;
  @FXML
  private Button btnSend;

  /**
   * Метод обработки событий для закрытия приложения
   */
  @FXML
  public void clickClose() {
    Platform.runLater(() -> {
      Stage stage = (Stage) btnSend.getScene().getWindow();
      stage.close();
    });
  }

  /**
   * Метод обработки событий отправки сообщений из textField в textArea
   */
  @FXML
  public void sendMsg() {
    textArea.appendText(textField.getText() + "\n");
    textField.clear();
    textField.requestFocus();   // Вернуть фокус на textField
  }
}
