package level3.hw1;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // The task 1
        String[] strArr = {"1", "2", "3", "4"};

        System.out.println("Исходны массив");
        for (String e : strArr) {
            System.out.print(e + ' ');
        }
        System.out.println();

        moveElemInArr(strArr, 0, 3);

        System.out.println("Конечный массив");
        for (String e : strArr) {
            System.out.print(e + ' ');
        }
        System.out.println();

        // The task 2
        System.out.println("Исходный тип " + strArr.getClass());
        System.out.println("Конечный тип " + transformTypeArr(strArr).getClass());
    }

    /**
     * Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
     */
    private static <T> T[] moveElemInArr(T[] arr, int srcPos, int dstPos) {
        if (srcPos >= arr.length || srcPos < 0 || dstPos >= arr.length || dstPos < 0) {
            System.out.println("Поменять элементы массива не возможно! Указаны не верно позиции элементов массива!");
            return arr;
        }
        T srcEl = arr[srcPos];
        arr[srcPos] = arr[dstPos];
        arr[dstPos] = srcEl;
        return arr;
    }

    /**
     * Написать метод, который преобразует массив в ArrayList;
     */
    private static <T> ArrayList<T> transformTypeArr(T[] arr){
        ArrayList<T> list = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
