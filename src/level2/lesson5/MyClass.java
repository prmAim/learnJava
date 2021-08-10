package level2.lesson5;

/**
 * 1) Создают одномерный длинный массив, например:
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */
public class MyClass {
  static final int SIZE = 10000000;
  static final int H = SIZE / 2;
  private final Object monitor = new Object();

  private float[] arr = new float[SIZE];

  /**
   * Заполняют этот массив единицами;
   */
  public void getInitMass() {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = 1;
    }
  }

  /**
   * Метод вычисления в один поток
   */
  public void calcArr() {
    long a = System.currentTimeMillis();    // начало время выполнения

    for (int i = 0; i < arr.length; i++) {
      arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
    System.out.println("Метод 1. Время выполнения: " + (System.currentTimeMillis() - a) + " сек.");
  }

  /**
   * Метод вычисления в два потока
   */
  public void calcArrInThread() {
    float[] arrFirst = new float[H];
    float[] arrSeconfd = new float[H];

    long a = System.currentTimeMillis();    // начало время выполнения

    // Деление массива по полам
    System.arraycopy(arr, 0, arrFirst, 0, H);
    System.arraycopy(arr, H, arrSeconfd, 0, H);

    // Описание работы потока 1
    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < arrFirst.length; i++) {
        arrFirst[i] = (float) (arrFirst[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
      }
    });

    // Описание работы потока 2
    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < arrSeconfd.length; i++) {
        arrSeconfd[i] = (float) (arrSeconfd[i] * Math.sin(0.2f + (H + i) / 5) * Math.cos(0.2f + (H + i) / 5) * Math.cos(0.4f + (H + i) / 2));
      }
    });
    // Запуск потоко 1 и 2
    thread1.start();
    thread2.start();

    // Ожидание потока main, пока отработает поток thread1 и thread2
    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //Обратное склеивание массивов
    System.arraycopy(arrFirst, 0, arr, 0, H);
    System.arraycopy(arrSeconfd, 0, arr, H, H);

    System.out.println("Метод 1. Время выполнения: " + (System.currentTimeMillis() - a) + " сек.");
  }
}
