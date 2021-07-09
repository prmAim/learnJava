package lessons.lesson5;

/**
 * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
 */
public class Employe {
  private String fio;              // ФИО
  private String position;        // Должность
  private String eMail;           // Электронная почта
  private String phoneNumber;     // Телефонный номер
  private float salary;           // Зарплата
  private int age;              // Возраст

  /**
   * Конструктор
   * @param fio - ФИО
   * @param position  - Должность
   * @param eMail     - Электронная почта
   * @param phoneNumber   - Телефонный номер
   * @param salary        - Зарплата
   * @param age           - Возраст
   */
  public Employe(String fio, String position, String eMail, String phoneNumber, float salary, int age) {
    this.fio = fio;
    this.position = position;
    this.eMail = eMail;
    this.phoneNumber = phoneNumber;
    this.salary = salary;
    this.age = age;
  }

  private Employe() {
  }

  /**
   * Getter
   */
  public String getFio() {
    return fio;
  }

  public String getPosition() {
    return position;
  }

  public String getEMail() {
    return eMail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public float getSalary() {
    return salary;
  }

  public int getAge() {
    return age;
  }

  /**
   * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
   */
  public void getInfoEmploye() {
    System.out.printf("ФИО = %s\nДолжность = %s\nЭлектронная почта = %s\n" +
            "Телефонный номер = %s\nЗарплата = %f руб.\nВозраст = %d лет\n", fio, position, eMail, phoneNumber, salary, age);
  }

  @Override
  public String toString() {
    return "Employe{" +
            "fio='" + fio + '\'' +
            ", position='" + position + '\'' +
            ", eMail='" + eMail + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", salary=" + salary +
            ", age=" + age +
            '}';
  }
}
