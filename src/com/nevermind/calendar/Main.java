package com.nevermind.calendar;

import java.time.DayOfWeek;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        try {
            //создаем календарь
            Calendar calendar = new Calendar(2, Month.MAY, 2020);

            //добавляем праздники с фиксированнй датой
            calendar.addHoliday(1, Month.JANUARY, "Новый Год");
            calendar.addHoliday(7, Month.JANUARY, "Рождество");
            calendar.addHoliday(14, Month.FEBRUARY, "День всех влюбленных");
            calendar.addHoliday(23, Month.FEBRUARY, "День защитников Отечества");
            calendar.addHoliday(8, Month.MARCH, "Международный женский день");
            calendar.addHoliday(1, Month.MAY, "День труда");
            calendar.addHoliday(9, Month.MAY, "День победы");

            //добавляем праздники с плавающей датой
            calendar.addHoliday(DayOfWeek.SUNDAY, 1, Month.APRIL, "День геолога");
            calendar.addHoliday(DayOfWeek.SUNDAY, 0, Month.MAY, "День химика");
            calendar.addHoliday(DayOfWeek.SUNDAY, 0, Month.SEPTEMBER, "День машиностроителя");

            //выводим на печать текущий день
            calendar.currentDayInfo();

            //смещаем текущую дату на день вперед
            calendar.nextDay();

            //выводим на печать текущий день
            calendar.currentDayInfo();

            //выводим на печать календарь на 2020
            calendar.printYear(2020);

            //выводим информацию об определнном дне
            calendar.dayInfo(9, Month.MAY, 2020);
        }
        catch (IllegalArgumentException iae) {
            System.err.println("Введены неверные данные.");
        }
    }
}
