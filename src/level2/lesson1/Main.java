package level2.lesson1;

public class Main {
    public static void main(String[] args) {
        // Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
        RunAndJumping[] members = {
                new Cat("Гав", 250, 2),
                new Cat("Мурка", 500, 2),
                new Human("Илья", 10000, 3),
                new Human("Добрыня", 50000, 3),
                new Robot("Терминатор", 1000000, 0),
                new Robot("Робокоп", 750000, 10)
        };

        /**
         * Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять
         * соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал,
         * не смог пробежать и т.д.).
         */
        Blockaging[] blocks = {
                new Racetrack("Футбольное поле", 90),
                new Wall("Шлагбаум", 1),
                new Racetrack("Лондонский марафон", 42000),
                new Racetrack("ЕКАД", 72000),
                new Wall("Китайская стена", 9)
        };

        for (int i = 0; i < members.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (!blocks[j].checkCanToDo(members[i])) {
                    break;
                }
            }
            System.out.println("<----->");

        }

    }
}
