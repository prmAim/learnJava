package level2.lesson1;

public class Wall implements Blockaging {
    private String nameWall;
    private int elevation;
    private final int STANDART_ELEV = 1;

    public Wall(String nameWall, int elevation) {
        this.nameWall = nameWall;
        this.elevation = checkElevation(elevation) ? elevation : STANDART_ELEV;
    }

    @Override
    public boolean checkCanToDo(RunAndJumping obj) {
        if (elevation <= obj.jump()) {
            System.out.printf("Участник %s смог перепрыгнуть препятсвие %s высотой %d \n", obj.getName(), nameWall, elevation);
            return true;
        }
        System.out.printf("Участник %s НЕ смог перепрыгнуть препятсвие %s высотой %d \n", obj.getName(), nameWall, elevation);
        return false;
    }

    /**
     * Проверка на получение корректной информации об высоте препятствия
     */
    private boolean checkElevation(int elev) {
        if (elev < 0) {
            System.out.println("Высоты с таким препадствием не бывает у объекта <" + nameWall + ">");
            return false;
        }
        return true;
    }
}
