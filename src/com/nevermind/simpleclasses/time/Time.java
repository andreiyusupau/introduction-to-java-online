package com.nevermind.simpleclasses.time;

/*6. Составьте описание класса для представления времени. Предусмотрте возможности установки времени и
изменения его отдельных полей (час, минута, секунда) с проверкой допустимости вводимых значений. В случае
недопустимых значений полей поле устанавливается в значение 0. Создать методы изменения времени на
заданное количество часов, минут и секунд.*/

public class Time {

    private int hours; //часы
    private int minutes; // минуты
    private int seconds; //секунды

    //установить время
    public void setTime(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    //установить часы
    public void setHours(int hours) {
        this.hours = hours > 23 || hours < 0 ? 0 : hours;
    }

    //установить минуты
    public void setMinutes(int minutes) {
        this.minutes = minutes > 59 || minutes < 0 ? 0 : minutes;
    }

    //установить секунды
    public void setSeconds(int seconds) {
        this.seconds = seconds > 59 || seconds < 0 ? 0 : seconds;
    }

    //добавить или отнять часы
    public void addHours(int hours) {
        setHours(this.hours + hours);
    }

    //добавить или отнять минуты
    public void addMinutes(int minutes) {

        //определяем суммарное число минут
        int totalMinutes;
        totalMinutes = this.minutes + this.hours * 60 + minutes;

        //если оно кратно 60 переводим часть в часы
        setHours(totalMinutes / 60);

        //остальное устанавливаем в минутах
        setMinutes(totalMinutes % 60);
    }

    //добавить или отнять секунды
    public void addSeconds(int seconds) {

        //определяем суммарное число секунд
        int totalSeconds;
        totalSeconds = this.seconds + this.minutes * 60 + this.hours * 3600 + seconds;

        //устанавливаем часы
        setHours(totalSeconds / 3600);

        //устанавливаем минуты
        setMinutes(totalSeconds % 3600 / 60);

        //устанавливаем секунды
        setSeconds(totalSeconds % 3600 % 60);
    }

    //показать текущее время
    public void show() {
        System.out.printf("%02d:%02d:%02d\n", hours, minutes, seconds);
    }
}
