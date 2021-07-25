package level2.lesson2;

/**
 * Если в каком-то элементе массива преобразование не удалось, должно быть брошено исключение MyArrayDataException,
 * с детализацией в какой именно ячейке лежат неверные данные.
 */
class MyArrayDataException extends NumberFormatException{
    private int numberLine;     // номер линии в массиве
    private int numberElement;  // номер элемента в строке массива

    public MyArrayDataException(String s, int numberLine, int numberElement) {
        super(s + " [" + numberLine + "][" + numberElement + "]");
        this.numberLine = numberLine;
        this.numberElement = numberElement;
    }

    public int getNumberLine() {
        return numberLine;
    }

    public int getNumberElement() {
        return numberElement;
    }
}
