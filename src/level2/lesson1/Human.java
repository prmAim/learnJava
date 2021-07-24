package level2.lesson1;

/**
 * Создайте класс Человек, который не наследуется от одного класса. Эти классы должны уметь бегать и
 * прыгать (методы просто выводят информацию о действии в консоль).
 */
class Human implements RunAndJumping {
    private String nameHuman;
    private int maxDistToRun;
    private int maxElevToJump;
    private final int STAND_DIST = 5000;
    private final int STAND_JUMP = 2;

    public Human(String nameHuman, int maxDistToRun, int maxElevToJump) {
        this.nameHuman = nameHuman;
        this.maxDistToRun = checkValue(maxDistToRun) ? maxDistToRun : STAND_DIST;
        this.maxElevToJump = checkValue(maxElevToJump) ? maxElevToJump : STAND_JUMP;
    }

    /**
     * Метод бежать
     */
    @Override
    public int run() {
        System.out.printf("%s может пробежать до %d метров\n", nameHuman, maxDistToRun);
        return maxDistToRun;
    }

    /**
     * Метод прыгать
     */
    @Override
    public int jump() {
        System.out.printf("%s может перепрыгнуть препятствие высотой до %d \n", nameHuman, maxElevToJump);
        return maxElevToJump;
    }

    @Override
    public String getName() {
        return nameHuman;
    }

    /**
     * Проверка на получение корректной информации об характеристиках объекта
     */
    private boolean checkValue(int Value) {
        if (Value <= 0) {
            System.out.printf("Характеристики с таким значением %d у объекта <%s> не бывает\n", Value, nameHuman);
            return false;
        }
        return true;
    }
}
