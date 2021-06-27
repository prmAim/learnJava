package lessons;

import java.util.Arrays;
import java.util.Random;

public class HomeWork3_22062021 {
    static Random random = new Random();

    public static void main(String[] agrs) {
        System.out.println("The task 1");
        getTask1();
        System.out.println();

        System.out.println("The task 2");
        System.out.println(Arrays.toString(createArrayLen100()));
        System.out.println();

        System.out.println("The task 3");
        getArrayMulti6();
        System.out.println();

        System.out.println("The task 4");
        getArraySquare(10);
        System.out.println();

        System.out.println("The task 5");
        printArray("Итоговый массив: ", getArrayConstantValue(15, 20));
        System.out.println();

        System.out.println("The task 6");
        getMinMaxIntoArray();
        System.out.println();

        System.out.println("The task 7");
        System.out.println("Результат задачи: " + checkBalance(createBorderSumHalfOfArray(createArrayBit(20, 100))));
        System.out.println();
        int[] arrayMain = new int[]{2, 2, 2, 1, 2, 2, 111, 10, 1};
        printArray("Итоговый массив: ", arrayMain);
        System.out.println("Результат задачи: " + checkBalance(arrayMain));
        System.out.println();

        System.out.println("The task 8");
        moveValueOfArray(createArrayBit(20, 100), 20);
        System.out.println();

    }

    /**
     * Задача 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    private static void getTask1() {
        int lenArray = 10;
        int[] arr = new int[lenArray];
        arr = createArrayBit(lenArray, 2);
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        System.out.println("Итоговый массив: " + Arrays.toString(getArrayBit(arr)));
    }

    /**
     * Создать одномерный масив из 0, 1
     *
     * @param lenArr длина массива
     * @return
     */
    private static int[] createArrayBit(int lenArr, int maxGeneratNumber) {
        int[] array = new int[lenArr];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxGeneratNumber);
        }
        return array;
    }

    /**
     * С помощью цикла и условия заменить 0 на 1, 1 на 0 в полученом цикле
     *
     * @param array
     * @return
     */
    private static int[] getArrayBit(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        return array;
    }

    /**
     * Задача 2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
     */
    private static int[] createArrayLen100() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    /**
     * Задача 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    private static void getArrayMulti6() {
        int[] array = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray("исходный массив: ", array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 6;
            }
        }
        printArray("Итоговый массив: ", array);
    }

    /**
     * Задача 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
     * заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно). Определить элементы одной
     * из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
     */
    private static void getArraySquare(int lenArray) {
        int[][] array = new int[lenArray][lenArray];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    array[i][j] = 1;
                }
                if (i == (lenArray - j - 1)) {
                    array[i][j] = 1;
                }
            }
        }
        printArray("Итоговый массив: ", array);
    }

    /**
     * Задача 5. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int
     * длиной len, каждая ячейка которого равна initialValue;
     */
    private static int[] getArrayConstantValue(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    /**
     * Задача 6. Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
     */
    private static void getMinMaxIntoArray() {
        int[] array = createArrayBit(20, 50);
        printArray("Исходный массив: ", array);
        int minNumber = array[0];
        int maxNumber = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minNumber >= array[i]) {
                minNumber = array[i];
            }
            if (maxNumber <= array[i]) {
                maxNumber = array[i];
            }
        }
        System.out.printf("Минимальное значение = %d, максимальное значение = %d", minNumber, maxNumber);
    }

    /**
     * Задача 7. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
     * если в массиве есть место, в котором сумма левой и правой части массива равны.
     */
    private static boolean checkBalance(int[] array) {
        int sumLeft = 0;
        int sumRight = 0;
        boolean isFinishSumLeft = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 111) {
                isFinishSumLeft = true;
                continue;
            }
            if (isFinishSumLeft == false) {
                sumLeft += array[i];
            } else {
                sumRight += array[i];
            }
        }
        return (sumLeft == sumRight) && isFinishSumLeft == true;
    }

    /**
     * Задача 8. Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
     * при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично. Для усложнения задачи
     * нельзя пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
     * [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону сдвиг можете выбирать сами.
     */
    private static int[] moveValueOfArray(int[] array, int n) {

        return array;
    }

    /**
     * Установить границу в массиме = 111, по которой будем определять половину левой и правой части массива
     */
    private static int[] createBorderSumHalfOfArray(int[] array) {
        printArray("Исходный массив: ", array);
        // Делим массив на части
        int isBorderOfArray = random.nextInt(array.length);
        // Если isBorderOfArray = 0 || isBorderOfArray = array.length, то считаем, что границы нет
        if (isBorderOfArray != 0 || isBorderOfArray != array.length) {
            array[isBorderOfArray] = 111;
        }
        printArray("Установили границу: ", array);
        return array;
    }

    /**
     * Данный метод распечатывает одномерный массив
     */
    private static void printArray(String str, int[] array) {
        System.out.print(str);
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%4d", array[i]);
        }
        System.out.println();
    }

    /**
     * Данный метод распечатывает двухмерный массив
     */
    private static void printArray(String str, int[][] array) {
        System.out.println(str);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%4d", array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
