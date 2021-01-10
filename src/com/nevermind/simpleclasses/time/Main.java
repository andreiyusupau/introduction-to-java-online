package com.nevermind.simpleclasses.time;

public class Main {

    public static void main(String[] args) {
//создали объект отражающий время
        Time t = new Time();

        //установили время
        t.setTime(12, 2, 53);

        //вывели на печать
        t.show();

        //отняли 5000 секунд
        t.addSeconds(-5000);

        //вывели на печать
        t.show();

        //установили часы на 8
        t.setHours(8);

        //вывели на печать
        t.show();

        //попытались установить секунды на 500 (не получилось)
        t.setSeconds(500);

        //вывели на печать
        t.show();
    }
}
