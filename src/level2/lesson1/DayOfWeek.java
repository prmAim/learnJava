package level2.lesson1;

/**
 * Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
 */
public enum DayOfWeek {
    MONDAY("Понедельник", true),
    TUESFAY("Вторник", true),
    WEDNESDAY("Среда", true),
    THURSDAY("Четверг", true),
    FRIDAY("Пятница", true),
    SATURDAY("Суббота", false),
    SUNDAY("Воскресенье", false);

    private String rusTitle;            // Русское наименование
    private boolean isWorkDay;          // рабочий/не рабочий день
    private final int WORK_HOUR = 8;    // Кол-во рабочих часов в день

    private DayOfWeek(String rusTitle, boolean isWorkDay) {
        this.rusTitle = rusTitle;
        this.isWorkDay = isWorkDay;
    }

    public String getRusTitle() {
        return rusTitle;
    }

    /**
     * Возвращает кол-во оставшихся рабочих часов до конца
     * недели по заданному текущему дню. Считается, что
     * текущий день ещё не начался, и рабочие часы за него
     * должны учитываться.
     * Если заданный день выходной то вывести "Сегодня выходной"
     */
    int getWorkingHours() {
        int sumНour = 0;
        if (this == DayOfWeek.SUNDAY || this == DayOfWeek.SATURDAY){
            System.out.println("Сегодня выходной!");
            return 0;
        }
        return (this.compareTo(DayOfWeek.SATURDAY)) * -1 * WORK_HOUR;
    }
}