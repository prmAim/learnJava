package lessons.lesson6.Animals;

/**
 * Класс Собака наследник класса Животное
 */
public class Dog extends Animal {
  private final int distToWater = 10;      // Дистанция, на которую могут плавать кошки, метр
  private final int distToAir = 500;      // Дистанция, на которую могут бегать кошки, метр
  private static int createDog = 0;       // хранение кол-во созданых объектов Cat

  public Dog(String name) {
    super(name);
    Dog.createDog++;
  }

  /**
   * поведение животного - бежать
   *
   * @param dist - дистанция, на которую должено пробежать животное
   */
  @Override
  public void printRun(int dist) {
    if (distToAir < dist) {
      System.out.printf("Собака по клички %s пробежала %d метров\n", name, distToAir);
    } else {
      System.out.printf("Собака по клички %s пробежала %d метров\n", name, dist);
    }
  }

  /**
   * поведение животного - плыть
   *
   * @param dist - дистанция, на которую должено пробежать животное
   */
  @Override
  public void printSwim(int dist) {
    if (distToWater < dist) {
      System.out.printf("Собака по клички %s проплыла %d метров\n", name, distToWater);
    } else {
      System.out.printf("Собака по клички %s проплыла %d метров\n", name, dist);
    }
  }

  /**
   * Кол-во созданных объектов класса Dog
   */
  public static int getCreateObjDog() {
    return createDog;
  }

}
