package level3.hw1.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples1 = new ArrayList<>(Arrays.asList(
                new Apple(1),
                new Apple(2),
                new Apple(3),
                new Apple(4)
        ));
        Box<Apple> box1 = new Box<>(apples1);

        List<Apple> apples2 = new ArrayList<>(Arrays.asList(
                new Apple(10),
                new Apple(11),
                new Apple(12),
                new Apple(13),
                new Apple(14),
                new Apple(15)
        ));
        Box<Apple> box2 = new Box<>(apples2);

        List<Orange> orange1 = new ArrayList<>(Arrays.asList(
                new Orange(1),
                new Orange(2),
                new Orange(3),
                new Orange(4)
        ));
        Box<Orange> box3 = new Box<>(orange1);

        List<Orange> orange2 = new ArrayList<>(Arrays.asList(
                new Orange(10)
        ));
        Box<Orange> box4 = new Box<>(orange2);

        System.out.println("Вес коробки box1 = " + box1.getWeight());
        System.out.println("Вес коробки box2 = " + box2.getWeight());
        System.out.println("Вес коробки box3 = " + box3.getWeight());
        System.out.println("Вес коробки box4 = " + box4.getWeight());

        System.out.println("Сравнение box1 и box2 = " + box1.compare(box2));
        System.out.println("Сравнение box2 и box3 = " + box2.compare(box3));

        box1.moveFruitBitweenBox(box1);
        box1.moveFruitBitweenBox(box2);
        System.out.println("Переложили. Вес коробки box1 = " + box1.getWeight());
        System.out.println("Переложили. Вес коробки box2 = " + box2.getWeight());

        box1.addFruitInBox(new Apple(1));
        System.out.println("Добавили. Вес коробки box1 = " + box1.getWeight());
    }
}
