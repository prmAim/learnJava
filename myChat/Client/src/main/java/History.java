import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс для сохранения истории чата каждого пользователя
 */
public class History {
  private static final String MASK_HISTORY_FILE = "history_";
  private static final String DIRECTORY = "history/";

  private static PrintWriter out;   // поток для сохранения данных в фаил

  /**
   * Получить относительный путь к файлу - истории пользователя
   */
  private static String getHistoryFilenameByLogin(String login){
    return  DIRECTORY + MASK_HISTORY_FILE + login + ".log";
  }

  /**
   * Создание потока на запись
   */
  public static void start(String login){
    // Открываем фаил на запись. Первый true - это дозапись файла, второй true - это отключение буфера (то есть не надо ждать, когда буфер заполниться и только потом пойдет запись)
    // либо сбросом командой flush
    try {
      out = new PrintWriter(new FileOutputStream(getHistoryFilenameByLogin(login), true), true);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Закрытие потока
   */
  public static void stop(){
    if (out != null){
      out.close();
      System.out.println("Поток закрыт");
    }
  }

  /**
   * Записать строку
   */
  public static void writeLine(String msg){
    out.println(msg);
  }

  /**
   * Прочитать историю последних 100 записей.
   */
  public static String getLast100LinesOfHistor(String login){
    if (!Files.exists(Paths.get(getHistoryFilenameByLogin(login)))){    // если фаил не существует, то вернум пусто
      return "";
    }
    StringBuilder msg = new StringBuilder();
    try {
      // читаем всю историю в лист.
      List<String> listHistor = Files.readAllLines(Paths.get(getHistoryFilenameByLogin(login)));
      int startPos = 0;
      if (listHistor.size() > 100) {
        startPos = listHistor.size() - 100;
      }
      for (int i = startPos; i < listHistor.size(); i++){
        msg.append(listHistor.get(i)).append(System.lineSeparator());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return  msg.toString();
  }

}
