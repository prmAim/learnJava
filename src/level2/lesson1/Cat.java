package level2.lesson1;

/**
 * Создайте класс Кот, который не наследуется от одного класса. Эти классы должны уметь бегать и
 * прыгать (методы просто выводят информацию о действии в консоль).
 */
class Cat implements RunAndJumping {
    private String nameCat;
    private int maxDistToRun;
    private int maxElevToJump;
    private final int STAND_RUN = 500;
    private final int STAND_JUMP = 1;

    public Cat(String nameCat, int maxDistToRun, int maxElevToJump) {
        this.nameCat = nameCat;
        this.maxDistToRun = checkValue(maxDistToRun) ? maxDistToRun : STAND_RUN;
        this.maxElevToJump = checkValue(maxElevToJump) ? maxElevToJump : STAND_JUMP;
    }

    /**
     * Метод бежать
     */
    @Override
    public int run() {
        System.out.printf("Кот по клички %s может пробежать до %d метров\n", nameCat, maxDistToRun);
        return maxDistToRun;
    }

    /**
     * Метод прыгать
     */
    @Override
    public int jump() {
        System.out.printf("Кот по клички %s может перепрыгнуть препятствие высотой до %d \n", nameCat, maxElevToJump);
        return maxElevToJump;
    }

    @Override
    public String getName() {
        return nameCat;
    }

    /**
     * Проверка на получение корректной информации об характеристиках объекта
     */
    private boolean checkValue(int Value) {
        if (Value <= 0) {
            System.out.printf("Характеристики с таким значением %d у объекта <%s> не бывает\n", Value, nameCat);
            return false;
        }
        return true;
    }
}
