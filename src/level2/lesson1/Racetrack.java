package level2.lesson1;

/**
 * Создайте класс: беговая дорожка, при прохождении через которые, участники должны выполнять соответствующие действия
 * (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
 */
public class Racetrack implements Blockaging {
    private String nameRace;
    private int distance;
    private final int STANDART_DIST = 5;

    public Racetrack(String nameRace, int distance) {
        this.nameRace = nameRace;
        this.distance = checkDistance(distance) ? distance : STANDART_DIST;
    }

    @Override
    public boolean checkCanToDo(RunAndJumping obj) {
        if (distance <= obj.run()) {
            System.out.printf("Участник %s смог пробегать %s дистанцией %d \n", obj.getName(), nameRace, distance);
            return true;
        }
        System.out.printf("Участник %s НЕ смог пробегать %s дистанцией %d \n", obj.getName(), nameRace, distance);
        return false;
    }

    /**
     * Проверка на получение корректной информации об дистанции
     */
    private boolean checkDistance(int dist) {
        if (dist < 0) {
            System.out.println("Высоты с таким препадствием не бывает у объекта <" + nameRace + ">");
            return false;
        }
        return true;
    }
}
