import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Класс проверки авторизации клиентов чкерез Базу Данных
 */
public class DBAuthService implements AuthService {
  private Statement stmtDB;      // ссылка на подключение оператора запросов к БД

  public DBAuthService(Statement stmtDB) {
    this.stmtDB = stmtDB;
  }

  @Override
  public String getNicknameByLoginAndPassword(String login, String password) {
    try {
      return executeGetNick(login, password);
    } catch (SQLException e) {
      System.out.println("LOG: Ошибка при прохождении аутентификации ! login =" + login);
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean registration(String login, String password, String nickname) {
    try {
      if (executeCheckLogin(login, nickname) == false) {
        return executeRegUser(login, password, nickname);
      }
    } catch (SQLException e) {
      System.out.println("LOG: Ошибка при регистрации нового пользователя! login =" + login);
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Запросы к базе данных: аутентификация клиента
   */
  private String executeGetNick(String login, String password) throws SQLException {
    ResultSet rs = stmtDB.executeQuery("SELECT nickname FROM user WHERE login = '" + login + "' and password = '" + password + "';");
    while (rs.next()) {
      return rs.getString("nickname");
    }
    return null;
  }

  /**
   * Запросы к базе данных: проверка на наличие клиента
   */
  private boolean executeCheckLogin(String login, String nickname) throws SQLException {
    ResultSet rs = stmtDB.executeQuery("SELECT userID FROM user WHERE login = '" + login + "' or nickname = '" + nickname + "';");
    while (rs.next()) {
      return rs.getInt("userID") > 0 ? true : false;
    }
    return false;
  }

  /**
   * Запросы к базе данных: добавление нового пользователя в базу данных
   */
  private boolean executeRegUser(String login, String password, String nickname) throws SQLException {
    int countRes = stmtDB.executeUpdate("INSERT INTO user (login, password, nickname) VALUES ('" + login + "', '" + password + "', '" + nickname + "');");
    return countRes > 0 ? true : false;
  }
}
