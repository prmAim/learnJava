package level3.hw1_1.fruit;

public abstract class Fruit {

  private Fruit() {
  }

  public Fruit(float weight) {
    this.weight = weight;
  }

  private float weight;

  public float getWeight() {
    return weight;
  }
}
