package level2.lesson2;

/**
 * Появиться исключение MyArraySizeException при подаче массива другого размера, кроме 4*4.
 */
class MyArraySizeException extends IllegalArgumentException{
    public MyArraySizeException(String s) {
        super(s);
    }
}
