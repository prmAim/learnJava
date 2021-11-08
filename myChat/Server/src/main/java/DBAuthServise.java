/**
 * Класс проверки авторизации клиентов.
 */
public class DBAuthServise implements AuthService{

  @Override
  public String getNicknameByLoginAndPassword(String login, String password) {
    return SQLHandler.getNicknameByLoginAndPassword(login, password);
  }

  @Override
  public boolean registration(String login, String password, String nickname) {
    return SQLHandler.registrationUser(login, password, nickname);
  }

  @Override
  public boolean changeNick(String oldNickname, String newNickname) {
    return SQLHandler.changeNickname(oldNickname, newNickname);
  }
}
