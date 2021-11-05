package level3.hw1.task3;

/**
 * Родительский абстрактный класс <Апельсин>
 */
public class Orange extends Fruit {
    private static final float WEIGHT_ORANGE = 1.5f;

    private float weight;

    public Orange(int number) {
        this.weight = WEIGHT_ORANGE;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
