package lessons.lesson6.Animals;

/**
 * Класс животные
 */
public class Animal {
  String name;
  private static int createAnimal = 0;

  // Создание родительского конструктора
  public Animal(String name) {
    this.name = name;
    Animal.createAnimal++;
  }

  /**
   * поведение животного - бежать
   *
   * @param dist - дистанция, на которую должено пробежать животное
   */
  public void printRun(int dist) {
    System.out.println(name + " умеет бегать");
  }

  /**
   * поведение животного - плыть
   *
   * @param dist - дистанция, на которую должено пробежать животное
   */
  public void printSwim(int dist) {
    System.out.println(name + " умеет плавать");
  }

  /**
   * Кол-во созданных объектов класса Animal
   */
  public static int getCreateObjAnimal() {
    return createAnimal;
  }

}
