package level3.hw1_1;

import level3.hw1_1.fruit.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс <Коробка>. Ограничение сверху Fruit. Хранит фрукты.
 */
public class Box<T extends Fruit> {
  private List<T> fruits;

  public Box() {
    this.fruits = new ArrayList<>();
  }

  public Box(T... fruits) {
    this.fruits = new ArrayList<>(Arrays.asList(fruits));
  }

  public List<T> getFruits() {
    return fruits;
  }

  public void setFruits(List<T> fruits) {
    this.fruits = fruits;
  }

  /**
   * высчитывает вес коробки
   *
   * @return вес коробки
   */
  public float getWeight() {
    float sumWeight = 0.0f;
    for (T fruit : fruits) {
      sumWeight += fruit.getWeight();
    }
    return sumWeight;
  }

  /**
   * Cравнение веса текущей коробки
   **/
  public boolean compare(Box<? extends Fruit> box2) {
    return Math.abs(this.getWeight() - box2.getWeight()) < 0.0001;
  }

  /**
   * Метод пересыпать фрукты из текущей коробки в другую
   */
  public void transferToDstBox(Box<T> dstBox){
    if (this == dstBox ){
      return;
    }
    dstBox.fruits.addAll(this.fruits);
    this.fruits.clear();
  }

  /**
   * Добавить фрукт в конзину
   */
  public void addFruitIntoBox(T fruit){
    this.fruits.add(fruit);
  }

  /**
   * Удалить все фрукты из коробки
   */
  public void removeFruitFromBox(){
    this.fruits.removeIf(x -> x != null);
  }
}
