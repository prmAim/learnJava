package level2.lesson2;

public class Main {
    public static void main(String[] args) {
        String[][] str = {
                {"12", "45", "55", "5"},
                {"12", "45", "55", "5"},
                {"12", "45", "55", "3"},
                {"12", "4о5", "55", "5"}
        };
        try {
            System.out.println(sumArr(str));
        } catch (MyArraySizeException obj) {
            System.out.println(obj.getMessage());
        } catch (MyArrayDataException obj) {
            System.out.println(obj.getMessage());
            str[obj.getNumberLine()][obj.getNumberElement()] = "0";
        }
        System.out.println("Конец программы");
    }

    /**
     * Сложить сумму элементов с обработкой исключений
     */
    private static int sumArr(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) {
            throw new MyArraySizeException("В массиме кол-во строк не равно 4.");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("В массиме кол-во элементов в строке не равно 4.");
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException obj) {
                    throw new MyArrayDataException("Не удалось преобразовать в цифровое значение элемент массива", i, j);
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += Integer.parseInt(arr[i][j]);
            }

        }

        return sum;
    }
}
