package lessons.lesson7;

public class Main {

  public static void main(String[] args) {

    Cat[] cats = {new Cat("Basik", 10),
            new Cat("Барсик", 15),
            new Cat("Рыжик", 5),
            new Cat("Васька", 20),
            new Cat("Мурка", 17),

    };

    Plate plate = new Plate(60);
    /**
     * Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию
     * о сытости котов в консоль.
     */
    for (int i = 0; i < cats.length; i++) {
      cats[i].eat(plate);
      System.out.printf("Кот по клички %s %s\n", cats[i].getNameCat(), cats[i].isHungerCat() ? " сытый" : "голодный");
    }

    /**
     *  Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
     */
    System.out.println("Осталось еды в тарелки = " + plate.getSizeFood());
    if (plate.addFood(20)) {
      System.out.println("Добавили еды в миску Осталось еды в тарелки = " + plate.getSizeFood());
    } else {
      System.out.println("Не удалось добавить еды в тарелку");
    }
  }
}
