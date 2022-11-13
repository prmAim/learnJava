package level3.hw1_1;

import level3.hw1_1.fruit.Apple;
import level3.hw1_1.fruit.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
//    Integer[] array = {1, 2, 3, 4, 5, 6};
    String[] array = {"1", "22", "111", "2222", "33333", "6"};
//    Double[] array = {0.3d, 2.6d, 7.3d};
    System.out.println("Задание 1.\n" +
            "Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)");
    System.out.print("  Исходный массив: ");
    System.out.println(Arrays.toString(array));

    changeElement(array, 0, 1);

    System.out.print("Результат массива: ");
    System.out.println(Arrays.toString(array));

    System.out.println("Задание 2. \n" +
            "Написать метод, который преобразует массив в ArrayList;");
    System.out.println(magicArrayToArrayList(array).getClass());
    System.out.println(magicArrayToArrayList(array));

    System.out.println("Задание 3. \n" +
            "Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;\n" +
            "Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;\n" +
            "Для хранения фруктов внутри коробки можно использовать ArrayList;\n" +
            "Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);\n" +
            "Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;\n" +
            "Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;\n" +
            "Не забываем про метод добавления фрукта в коробку.\n");
    Box<Apple> appleBox1 = new Box<>(new Apple(), new Apple(), new Apple());
    Box<Apple> appleBox2 = new Box<>(new Apple());
    Box<Orange> orangeBox1 = new Box<>(new Orange(), new Orange());
    Box<Orange> orangeBox2 = new Box<>(new Orange(), new Orange(), new Orange(), new Orange());

    System.out.println("Вес коробки яблок1 =" + appleBox1.getWeight() + " Вес коробки апельсинов1 =" + orangeBox1.getWeight() + " Сравнить=" + appleBox1.compare(orangeBox1));
    System.out.println("Вес коробки яблок1 =" + appleBox1.getWeight() + " Вес коробки апельсинов2 =" + orangeBox2.getWeight() + " Сравнить=" + appleBox1.compare(orangeBox2));
    System.out.println("Вес коробки яблок2 =" + appleBox2.getWeight() + " Вес коробки апельсинов1 =" + orangeBox1.getWeight() + " Сравнить=" + appleBox2.compare(orangeBox1));
    System.out.println("Вес коробки яблок2 =" + appleBox2.getWeight() + " Вес коробки апельсинов2 =" + orangeBox2.getWeight() + " Сравнить=" + appleBox2.compare(orangeBox2));

    System.out.println("\n Пересыпаем фрукты");
    appleBox1.putToDstBox(appleBox2);
    System.out.println("Вес коробки яблок1 =" + appleBox1.getWeight());
    System.out.println("Вес коробки яблок2 =" + appleBox2.getWeight());
    appleBox1.putToDstBox(appleBox2);
    System.out.println("Вес коробки яблок1 =" + appleBox1.getWeight());
    System.out.println("Вес коробки яблок2 =" + appleBox2.getWeight());
    orangeBox2.putToDstBox(orangeBox2);
    System.out.println("Вес коробки апельсинов1 =" + orangeBox1.getWeight());
    System.out.println("Вес коробки апельсинов2 =" + orangeBox2.getWeight());
    orangeBox2.putToDstBox(orangeBox1);
    System.out.println("Вес коробки апельсинов1 =" + orangeBox1.getWeight());
    System.out.println("Вес коробки апельсинов2 =" + orangeBox2.getWeight());
  }

  /**
   * Метод меняет два элемента массива местами
   *
   * @param arr  - массив
   * @param src  - элемент, который меняем на dest
   * @param dest - элемент, который меняется на  src
   */
  static <T> void changeElement(T[] arr, int src, int dest) {
    if (src >= arr.length) {
      throw new ArrayIndexOutOfBoundsException("This array index = " + src + " is  greater than the length of the array = " + (arr.length - 1));
    }
    if (dest >= arr.length) {
      throw new ArrayIndexOutOfBoundsException("This array index = " + dest + " is src greater than the length of the array = " + (arr.length - 1));
    }
    if (src != dest) {
      T temp = arr[src];
      arr[src] = arr[dest];
      arr[dest] = temp;
    }
  }

  /**
   * Написать метод, который преобразует массив в ArrayList
   */
  static <T> List<T> magicArrayToArrayList(T[] arr){
    return new ArrayList<T>(Arrays.asList(arr));
  }
}
