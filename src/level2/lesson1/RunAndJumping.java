package level2.lesson1;
/**
 * Эти классы должны уметь бегать и прыгать
 */
public interface RunAndJumping {
    /**
     * Контракт на метод бегать
     */
    int run();

    /**
     * Контракт на метод прыгать
     */
    int jump();

    /**
     * Контракт на имя учасника
     */
    String getName();
}
