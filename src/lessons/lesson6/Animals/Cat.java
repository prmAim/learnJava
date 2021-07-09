package lessons.lesson6.Animals;

/**
 * Класс Кот наследник класса Животное
 */
public class Cat extends Animal {
  private final int distToWater = 0;      // Дистанция, на которую могут плавать кошки, метр
  private final int distToAir = 200;      // Дистанция, на которую могут бегать кошки, метр
  private static int createCat = 0;       // хранение кол-во созданых объектов Cat

  public Cat(String name) {
    super(name);
    Cat.createCat++;
  }

  /**
   * поведение животного - бежать
   *
   * @param dist - дистанция, на которую должено пробежать животное
   */
  @Override
  public void printRun(int dist) {
    if (distToAir < dist) {
      System.out.printf("Кот по клички %s пробежал %d метров\n", name, distToAir);
    } else {
      System.out.printf("Кот по клички %s пробежал %d метров\n", name, dist);
    }
  }

  /**
   * поведение животного - плыть
   *
   * @param dist - дистанция, на которую должено пробежать животное
   */
  @Override
  public void printSwim(int dist) {
    System.out.println("Кот " + name + " не умеет плавать");
  }

  /**
   * Кол-во созданных объектов класса Cat
   */
  public static int getCreateObjCat() {
    return createCat;
  }

}
