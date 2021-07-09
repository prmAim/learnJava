package lessons.lesson7;

public class Plate {
  private int sizeFood;  // кол-во еды на тарелки.
  private static final int SIZE_FOOD_DEFAULT = 20; // кол-во еды на тарелки. По умолчанию = 20

  public Plate(int sizeFood) {
    if (sizeFood <= 0) {
      System.out.println("Объем еды на тарелки не может быть < 0");
      this.sizeFood = Plate.SIZE_FOOD_DEFAULT;
    }
    this.sizeFood = sizeFood;
  }

  public int getSizeFood() {
    return sizeFood;
  }

  /**
   * Убавить кол-во еды из тарелки.
   *
   * @return true - успешно уменьшили  еды из тарелки, false - не поели из тарелки.
   */
  public boolean getDecrease(int minusFood) {
    if (getIsEmptyPlate(minusFood)) {
      return false;
    }
    sizeFood -= minusFood;
    return true;
  }

  /**
   * Состояние кол-во еды на тарелки. Пустая или нет.
   *
   * @return false - не пустая тарелка, true - пустая тарелка
   */
  private boolean getIsEmptyPlate(int minusFood) {
    return sizeFood - minusFood <= 0;
  }


  public boolean addFood (int sizeFood) {
    if (sizeFood <= 0 ){
      return false;
    }
    this.sizeFood += sizeFood;
    return true;
  }
}
