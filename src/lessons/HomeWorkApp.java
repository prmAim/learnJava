package lessons;

public class HomeWorkApp {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    private static void printThreeWords() {
        System.out.printf("%s%n%s%n%s%n", "Orange", "Banana", "Apple");

    }

    private static void checkSumSign() {
        int a, b;
        a = 2;
        b = -3;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }

    }

    private static void printColor() {
        int value = 100;
        if (value <= 0) {
            System.out.println("Красный");
            return;
        }
        if (value > 0 && value <= 100) {
            System.out.println("Желтый");
            return;
        }
        System.out.println("Зеленый");
    }

    private static void compareNumbers() {
        int a, b;
        a = 0;
        b = 10;
        if (a >= b) {
            System.out.println("a >= b");
            return;
        }
        System.out.println("a < b");
    }
}
