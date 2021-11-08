import java.sql.*;

/**
 * Логический слой SQL
 */
public class SQLHandler {
  private static Connection connectDB;                  // соединение с драйвером БД

  private static PreparedStatement psGetNickname;       //  Подготовленный запрос
  private static PreparedStatement psRegistration;      //  Подготовленный запрос
  private static PreparedStatement psChangeNick;        //  Подготовленный запрос
  private static PreparedStatement psAddMessage;        //  Подготовленный запрос
  private static PreparedStatement psGetMessageForNick; //  Подготовленный запрос

  /**
   * Подключаем к БД
   */
  public static boolean connectToDB() {
    try {
      Class.forName("org.sqlite.JDBC");                                     // загрузка класса. А класс - это драйвер JDBC к SQLite [блок static]
      connectDB = DriverManager.getConnection("jdbc:sqlite:chat.db");   // подключаем драйвер к БД SQLite, используя DriverManager (знает о всех БД)
      System.out.println("LOG: Connect DB!");
      prepareAllStatements();                                               // Подготовительные запросы
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Отключение от БД
   */
  public static void disconnectToDB() {
    try {
      psAddMessage.close();
      psRegistration.close();
      psGetNickname.close();
      psChangeNick.close();
      psGetMessageForNick.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connectDB.close();
        System.out.println("LOG: Disconnect DB!");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Все подготовительные запросы
   */
  private static void prepareAllStatements() throws SQLException {
    // Получить имя псевдоним пользователя
    psGetNickname = connectDB.prepareStatement("" +
            "SELECT nickname \n" +
            "  FROM user \n" +
            " WHERE login    = ? \n" +
            "   AND password = ? ;");

    // Регистрация нового пользователя
    psRegistration = connectDB.prepareStatement("" +
            "INSERT INTO user \n" +
            " (login, password, nickname) \n" +
            "VALUES (?, ?, ?)");

    // Смена псевдонима у пользователя
    psChangeNick = connectDB.prepareStatement("" +
            "UPDATE user \n" +
            "  SET nickname = ? \n" +
            "WHERE nickname = ?");

    // Логирование переписки пользователя
    psAddMessage = connectDB.prepareStatement("" +
            "INSERT INTO messages \n" +
            "      (senderID, receiverID, messageTxt, dat, isBroadcast) \n" +
            "SELECT send.userID, \n" +
            "       rec.userID,  \n" +
            "       ?, \n" +
            "       datetime('now','localtime'), \n" +
            "       ? \n" +
            "  FROM user      AS send \n" +
            " INNER JOIN user AS rec  \n" +
            "    ON 1 = 1 \n" +
            " WHERE send.nickname = ? \n" +
            "   AND rec.nickname  = ?");

    // Получить историю переписки пльзователя
    psGetMessageForNick = connectDB.prepareStatement("" +
            "SELECT send.nickname AS sender, \n" +
            "       rec.nickname  AS receiver, \n" +
            "       msg.messageTxt, \n" +
            "       datetime(msg.dat) AS dat, \n" +
            "       isBroadcast \n" +
            "  FROM user          AS usr \n" +
            " INNER JOIN messages AS msg \n" +
            "    ON msg.senderID   = usr.userID \n" +
            "    OR msg.receiverID = usr.userID \n" +
            "INNER JOIN user  AS send \n" +
            "   ON send.userID = msg.senderID \n" +
            "INNER JOIN user AS rec \n" +
            "   ON rec.userID = msg.receiverID \n" +
            "WHERE usr.nickname    = ?" +
            "  AND msg.isBroadcast = false \n" +
            "   OR usr.nickname    = 'all' \n" +
            "ORDER BY msg.dat");
  }

  /**
   * Получить псевдоним пользователя
   *
   * @param login
   * @param password
   * @return nickname
   */
  public static String getNicknameByLoginAndPassword(String login, String password) {
    String nickname = null;
    try {
      psGetNickname.setString(1, login);       // передаем данные № 1 в подготовительный запрос psGetNickname
      psGetNickname.setString(2, password);    // передаем данные № 2 в подготовительный запрос psGetNickname
      ResultSet rs = psGetNickname.executeQuery();           // выполняем подготовительные вопрос
      if (rs.next()) {
        nickname = rs.getString("nickname");
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return nickname;
  }

  /**
   * Регистрация нового пользователя. Если пользователь или псевдоним пользователя уже есть в БД, то ошибка.
   *
   * @param login
   * @param password
   * @param nickname
   * @return true - регистрация прошла удачно, false - неудачно.
   */
  public static boolean registrationUser(String login, String password, String nickname) {
    try {
      psRegistration.setString(1, login);
      psRegistration.setString(2, password);
      psRegistration.setString(3, nickname);
      psRegistration.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Смена псевдонима пользователя. Если такой всевдоним уже есть, то ошибка.
   *
   * @param oldNickname
   * @param newNickname
   * @return true - смена псевдонима прошла удачно, false - неудачно.
   */
  public static boolean changeNickname(String oldNickname, String newNickname) {
    try {
      psChangeNick.setString(1, newNickname);
      psChangeNick.setString(2, oldNickname);
      psChangeNick.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean addMessageLog(String sender, String receiver, String messageTxt, boolean isBroadcast) {
    try {
      psAddMessage.setString(1, messageTxt);
      psAddMessage.setBoolean(2, isBroadcast);
      psAddMessage.setString(3, sender);
      psAddMessage.setString(4, receiver);
      System.out.println("LOG:" + psAddMessage.toString());
      psAddMessage.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Метод извлечения сообщений из БД извлекаются все сообщения пользователя с ником nick, отправленные им и приходящие к нему
   *
   * @param nickname - рсевдоним пользователя
   * @return список сообщений
   */
  public static String getMessageForNickname(String nickname) {
    StringBuilder sb = new StringBuilder();
    String sender = "";
    String receiver = "";
    String text = "";
    String date = "";
    try {
      psGetMessageForNick.setString(1, nickname);
      ResultSet rs = psGetMessageForNick.executeQuery();
      while (rs.next()) {
        sender = rs.getString("sender");
        receiver = rs.getString("receiver");
        text = rs.getString("messageTxt");
        date = rs.getString("dat");
        boolean isBroadcast = rs.getBoolean("isBroadcast");
        if (isBroadcast) {
          sb.append(String.format("[%s] : %s \n", sender, text));
        } else {
          sb.append(String.format("[%s] to [%s] : %s \n", sender, receiver, text));
        }
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

}
