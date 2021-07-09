package lessons;

public class HomeWork18062021 {
    public static void main(String[] args) {
        System.out.println(getCompareSum(5, 3));
        compareNumber(-1);
        System.out.println(compareNumberWith0(-1));
        getPrintln("Hello world!", 3);
        System.out.println(isYearViysokosniy(1600));
    }

    /**
     * Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20
     * (включительно)
     */
    private static boolean getCompareSum(int a, int b) {
        return (a + b >= 10 && a + b <= 20);
    }

    /**
     * Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
     * положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
     */
    private static void compareNumber(int num) {
        if (num >= 0) {
            System.out.println("Число передали положительное.");
        } else {
            System.out.println("Число передали отрицательное.");
        }
    }

    /**
     * Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число
     * отрицательное, и вернуть false если положительное.
     * @param num
     * @return
     */
    private static boolean compareNumberWith0 (int num){
        return (num >=0);
    }

    /**
     * Написать метод, которому в качестве аргументов передается строка и число, метод должен отпечатать в консоль указанную строку, указанное количество раз;
     */
    private static void getPrintln(String str, int step){
        for (int i = 0;  i < step; i++) {
            System.out.println(str);
        }
    }
    /**
     *Написать метод, который определяет, является ли год високосным, и возвращает boolean (високосный - true, не високосный - false).
     * Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
     */
    private static boolean isYearViysokosniy(int year){
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }
}
