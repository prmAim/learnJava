package lessons;

import java.util.Random;
import java.util.Scanner;

public class HomeWork_25062021 {
    // Константа - размер поля
    static final int SIZE = 5;
    // Констатнта - кол-во ячеек для победы
    static final int DOTS_TO_WIN = 4;
    // Константа - ход Игрока
    static final char DOT_X = 'X';
    // Констатнта - ход ПК
    static final char DOT_O = 'O';
    // Константа - пустая яцейка
    static final char DOT_EMPTY = '.';

    static char[][] map;
    // Ссылка - константа для ввода данных
    static final Scanner sc = new Scanner(System.in);
    // Ссылка - константа для генератора чисел
    static final Random random = new Random();

    public static void main(String[] args) {
        boolean isChckSetpToWin = true;
        initMap();
        printMap();
        if (DOTS_TO_WIN > SIZE) {
            System.out.println("Не соблюдены условия победы! Количество ячеек карты меньше суммы кол-во знаков для победы");
            isChckSetpToWin = false;
        }
        while (isChckSetpToWin) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил. Сейчас их даже в шахматы не выиграть...");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    /**
     * Создание первоначальной матрицы
     */
    public static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Построение матрицы и ее отрисовка
     */
    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Ход игрока. Проверка на заполненость ячейки.
     */
    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("input Y X");
            y = sc.nextInt() - 1;
            x = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    /**
     * Ход компютера. Генерация координат и установка 0. Проверка на заполненость ячейки.
     */
    public static void aiTurn() {
        int x;
        int y;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    /**
     * Проверка на свободное значение в ячейке
     */
    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка на заполненость матрицы
     */
    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * УСЛОВИЯ ПОБЕДЫ.
     * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
     * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
     */
    public static boolean checkWin(char c) {
        int sumWinX, sumWinY, sumWinXY, sumWinZ;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sumWinX = 0;
                sumWinY = 0;
                sumWinXY = 0;
                sumWinZ = 0;
                for (int k = 0; k < DOTS_TO_WIN; k++) {
                    // проверка на условие победы по оси Х
                    if (j + k < SIZE) {
                        if (map[i][j + k] == c) {
                            sumWinX++;
                        }
                    }
                    // проверка на условие победы по оси Y
                    if (i + k < SIZE) {
                        if (map[i + k][j] == c) {
                            sumWinY++;
                        }
                    }
                    // проверка на условие победы по диагонали вниз
                    if (j + k < SIZE && i + k < SIZE) {
                        if (map[i + k][j + k] == c) {
                            sumWinXY++;
                        }
                    }
                    // проверка на условие победы по гиагонали вверх
                    if (j + k < SIZE && i - k > 0) {
                        if (map[i - k][j + k] == c) {
                            sumWinZ++;
                        }
                    }
                    if (sumWinX == DOTS_TO_WIN || sumWinY == DOTS_TO_WIN || sumWinXY == DOTS_TO_WIN || sumWinZ == DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

}
