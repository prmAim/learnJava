package level2.lesson3;

import java.util.*;

/**
 * Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 */
class Phonebook {
  private Map<String, Set<Integer>> phBook = new HashMap<>();

  /**
   * Добавление в телефонный справочник Имя и его номер телефона
   */
  void add(String name, Integer... phoneNumber) {
    Set<Integer> phones = new HashSet<>(Arrays.asList(phoneNumber));
    if (phBook.get(name) == null) {
      phBook.put(name, phones);
    } else {
      phBook.get(name).addAll(phones);
    }
  }
  /**
   * С помощью метода get() искать номер телефона по фамилии.
   * * Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны
   * * выводиться все телефоны.
   */
  Set<Integer> get(String name) {
    return phBook.getOrDefault(name, new HashSet<>(Arrays.asList(0)));
  }

}
