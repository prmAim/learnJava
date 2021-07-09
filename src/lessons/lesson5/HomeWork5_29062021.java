package lessons.lesson5;

import java.util.Arrays;

public class HomeWork5_29062021 {

  public static void main(String[] args) {
    // Создать массив из 5 сотрудников.
    Employe[] employes = {
            new Employe("Попов Алексей Силович", "Разработчик", "test@email.ru", "8(342)01", 60000.00f, 25),
            new Employe("Сидоров Добрыня Никитич", "Старший Разработчик", "test2@email.ru", "8(342)02", 80000.00f, 30),
            new Employe("Толстой Лев Николаевич", "Ведущий Разработчик", "test3@email.ru", "8(342)03", 100000.00f, 35),
            new Employe("Пушкин Александр Сергеевич", "Архитектор", "test4@email.ru", "8(342)04", 1200000.00f, 41),
            new Employe("Лермонтов Михаил Юрьевич", "Тимлид", "test5@email.ru", "8(342)05", 1600000.00f, 45)
    };

    // С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
    for (int i = 0; i < employes.length; i++) {
      if (employes[i].getAge() > 40) {
        employes[i].getInfoEmploye();
        System.out.println();
      }
    }
  }
}
