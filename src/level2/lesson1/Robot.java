package level2.lesson1;

/**
 * Создайте класс Робот, который не наследуется от одного класса. Эти классы должны уметь бегать и прыгать
 * (методы просто выводят информацию о действии в консоль).
 */
class Robot implements RunAndJumping {
    private String nameRobot;
    private int maxDistToRun;
    private int maxElevToJump;
    private final int STAND_DIST = 1000000;
    private final int STAND_JUMP = 1;

    public Robot(String nameRobot, int maxDistToRun, int maxElevToJump) {
        this.nameRobot = nameRobot;
        this.maxDistToRun = checkValue(maxDistToRun) ? maxDistToRun : STAND_DIST;
        this.maxElevToJump = checkValue(maxElevToJump) ? maxElevToJump : STAND_JUMP;
    }

    /**
     * Метод бежать
     */
    @Override
    public int run() {
        System.out.printf("Робот %s может пробежать до %d метров\n", nameRobot, maxDistToRun);
        return maxDistToRun;
    }

    /**
     * Метод прыгать
     */
    @Override
    public int jump() {
        System.out.printf("Робот %s может перепрыгнуть препятствие высотой до %d \n", nameRobot, maxElevToJump);
        return maxElevToJump;
    }

    @Override
    public String getName() {
        return nameRobot;
    }

    /**
     * Проверка на получение корректной информации об характеристиках объекта
     */
    private boolean checkValue(int Value) {
        if (Value <= 0) {
            System.out.printf("Характеристики с таким значением %d у объекта <%s> не бывает\n", Value, nameRobot);
            return false;
        }
        return true;
    }
}
