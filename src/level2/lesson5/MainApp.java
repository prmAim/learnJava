package level2.lesson5;

public class MainApp {
  public static void main(String[] args) {
    MyClass twoThread = new MyClass();

    twoThread.getInitMass();
    twoThread.calcArrInThread();

    MyClass oneThread = new MyClass();
    oneThread.getInitMass();
    oneThread.calcArr();
  }
}
