package level2.lesson3;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    //Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
    //из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
    List<String> list = new ArrayList<>(Arrays.asList(
            "Привет",
            "мир",
            "Тревога",
            "Предупреждение",
            "Внимание",
            "Опасность",
            "Авария",
            "Опасность",
            "Предупреждение",
            "Авария",
            "мир",
            "Авария",
            "мир",
            "Опасность",
            "Авария",
            "Опасность"
    ));

    Map<String, Integer> map = new HashMap<>();

    for (int i = 0; i < list.size(); i++) {
      map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
    }

    map.forEach((key, value) -> System.out.println("Слово <" + key + "> встречание " + value + " раз."));

    // Проверка класса Phonebook
    Phonebook ph = new Phonebook();
    ph.add("Добрыня", 2267225, 2550577, 2460600, 2575789, 2988987);
    ph.add("Добрыня", 2550577);
    ph.add("Добрыня", 2267225);
    ph.add("Илья", 2460600);

    System.out.println("Проверка справочника <Добрыня> = " + ph.get("Добрыня"));
    System.out.println("Проверка справочника <Илья> = " + ph.get("Илья"));
    System.out.println("Проверка справочника <Соловей> = " + ph.get("Соловей"));
  }
}
