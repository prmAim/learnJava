package level2.lesson1;

/**
 * участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем
 * в консоль (успешно пробежал, не смог пробежать и т.д.).
 */
@FunctionalInterface
public interface Blockaging {
    /**
     * Проверка, сможет ли участки справиться c препятствием
     */
    boolean checkCanToDo(RunAndJumping obj);
}