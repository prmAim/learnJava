import java.util.ArrayList;
import java.util.List;

/**
 * Класс проверки авторизации клиентов.
 */
public class SimpleAuthService implements AuthService {
  /**
   * Внутрений класс описание клиентов
   */
  private class UserData {
    String login;
    String password;
    String nickname;

    public UserData(String login, String password, String nickname) {
      this.login = login;
      this.password = password;
      this.nickname = nickname;
    }
  }

  private List<UserData> users;

  /**
   * Конструктор создания списка клиентов, которые могут авторизоваться на сервере.
   */
  public SimpleAuthService() {
    this.users = new ArrayList<>();
    users.add(new UserData("qwe", "qwe", "qwe"));
    users.add(new UserData("asd", "asd", "asd"));
    users.add(new UserData("zxc", "zxc", "zxc"));
    for (int i = 1; i < 10; i++) {
      users.add(new UserData("login" + i, "pass" + i, "nick" + i));
    }
  }

  /**
   * Аутентификация клиента. Если логин и пароль совпадают, то возвращаем псевдоним клиента, иначе null.
   *
   * @param login    логин, не должен содержать пробелов
   * @param password пароль, не должен содержать пробелов
   * @return
   */
  @Override
  public String getNicknameByLoginAndPassword(String login, String password) {
    for (UserData u : users) {
      if (u.login.equals(login) && u.password.equals(password)) {
        return u.nickname;
      }
    }

    return null;
  }

  @Override
  public boolean registration(String login, String password, String nickname) {
    for (UserData u : users) {
      if (u.login.equals(login) || u.nickname.equals(nickname)) {
        return false;
      }
    }
    users.add(new UserData(login, password, nickname));
    return true;
  }

  @Override
  public boolean changeNick(String oldNickname, String newNickname) {
    return false;
  }
}
