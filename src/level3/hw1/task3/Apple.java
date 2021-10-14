package level3.hw1.task3;

/**
 * Родительский абстрактный класс <Яблоки>
 */
public class Apple extends Fruit {
    private static final float WEIGHT_APPLE = 1.0f;

    private float weight;

    public Apple(int number) {
        this.weight = WEIGHT_APPLE;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
