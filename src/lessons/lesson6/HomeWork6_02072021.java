package lessons.lesson6;

import lessons.lesson6.Animals.*;

public class HomeWork6_02072021 {
  public static void main(String[] args) {
    Animal[] arrAnimal = {
            new Cat("Барсик"),
            new Dog("Тузик"),
            new Dog("Боб")
    };
    int k = 2;
    for (int i = 0; i < arrAnimal.length; i++) {
      for (int j = 11; j < 650; j += 150) {
        arrAnimal[i].printRun(j);
        arrAnimal[i].printSwim(k);
        k++;
      }
      System.out.println();
    }
    System.out.println("Кол-во созданных объектов Animal = " + Animal.getCreateObjAnimal());
    System.out.println("Кол-во созданных объектов Cat = " + Cat.getCreateObjCat());
    System.out.println("Кол-во созданных объектов Dog = " + Dog.getCreateObjDog());

  }

}
