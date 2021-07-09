package lessons.lesson7;

public class Cat {
  private String nameCat;
  private int appetiteMaxCat;
  private boolean hungerCat;
  private static final int APPETITE_CAT_DEFAULT = 10;

  // конструктор
  private Cat(String nameCat, int appetiteMaxCat, boolean hungerCat) {
    this.nameCat = nameCat;
    if (appetiteMaxCat <= 0) {
      System.out.printf("Аппетит кота по клички %s не может быть < 0. Присвоено значение %d\n", nameCat, Cat.APPETITE_CAT_DEFAULT);
      this.appetiteMaxCat = Cat.APPETITE_CAT_DEFAULT;
    }
    this.appetiteMaxCat = appetiteMaxCat;
    this.hungerCat = hungerCat;
  }
  // конструктор
  public Cat(String nameCat, int appetiteMaxCat) {
    this(nameCat, appetiteMaxCat, false);
  }

  public String getNameCat() {
    return nameCat;
  }

  public boolean isHungerCat() {
    return hungerCat;
  }

  /**
   * кот поел из тарелки
   * @return true - поел, false - не поел
   */
  public boolean eat(Plate plate) {
    if (plate.getDecrease(appetiteMaxCat)){
      hungerCat = true;
      return true;
    }
     return false;
  }
}
