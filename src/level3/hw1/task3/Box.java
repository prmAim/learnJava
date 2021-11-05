package level3.hw1.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс <Коробка>. Ограничение с верху. Хранит фрукты
 */
public class Box<T extends Fruit> {
    private List<T> list = new ArrayList<>();

    public Box(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    /**
     * Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
     */
    public float getWeight() {
        if (list.isEmpty()) {
            return 0;
        }
        return list.get(0).getWeight() * list.size();
    }

    /**
     * Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
     * Можно сравнивать коробки с яблоками и апельсинами;
     */
    public boolean compare(Box<?> boxDst) {
        if (Math.abs(this.getWeight() - boxDst.getWeight()) < 0.0000001) {
            return true;
        }
        return false;
    }

    /**
     * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
     * Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
     * Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
     * которые были в первой;
     */
    public boolean moveFruitBitweenBox(Box<T> boxDst) {
        if (list.isEmpty() || this == boxDst) {
            return false;
        }
        boxDst.getList().addAll(list);
        list.clear();
        return true;
    }

    /**
     * метод добавления фрукта в коробку.
     */
    public void addFruitInBox(T obj) {
        list.add(obj);
    }
}
