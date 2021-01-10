package com.nevermind.calendar;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;
import java.util.stream.Stream;

/*Задача 3.
Создать класс Календарь с внутренним классом, с помощью объектов которого можно хранить информацию о
выходных и праздничных днях.*/

//класс Календарь
public class Calendar {

    int currentDay; //текущий день
    Month currentMonth; //текущий месяц
    int currentYear; //текущий год
    DayOfWeek currentDayOfWeek; //текущуий день недели
    Holiday[] holidays; //список праздников

    //конструктор
    public Calendar(int currentDay, Month currentMonth, int currentYear) throws IllegalArgumentException {
        if (currentDay > 0 && currentDay <= currentMonth.length(isLeap(currentYear)) && currentYear >= 0) {
            this.currentDay = currentDay;
            this.currentMonth = currentMonth;
            this.currentYear = currentYear;
            this.currentDayOfWeek = calcDayOfWeek(currentDay, currentMonth, currentYear);
            this.holidays = new Holiday[0];
        } else {
            throw new IllegalArgumentException();
        }
    }

    //перейти на следующий день
    public void nextDay() {

        currentDay += 1; //увеличиваем значение текущего дня на 1

        currentDayOfWeek = currentDayOfWeek.plus(1); //заменяем текущий день недели на следующий

        //если текущий день выходит за пределы количества дней в месяце
        if (currentDay > currentMonth.length(isLeap(currentYear))) {

            //переходим на нвоый месяц
            int monthNum;
            monthNum = currentMonth.getValue() + 1;

            //если месяц выходит за пределы года
            if (monthNum > 12) {

                monthNum = 1; //присваиваем ему значение "Январь"

                currentYear += 1; //увеличиваем значение года на 1
            }
            currentMonth = Month.of(monthNum); //устанавливаем значение текущего месяца
        }

    }

    //вывести информацию о текущем дне
    public void currentDayInfo() {
        dayInfo(currentDay, currentMonth, currentYear);
    }

    //вывести информацию о конкретной дате
    public void dayInfo(int day, Month month, int year) throws IllegalArgumentException {

        if (day > 0 && day <= month.length(isLeap(year)) && year >= 0) {

            DayOfWeek dayOfWeek;
            dayOfWeek = calcDayOfWeek(day, month, year); //определяем день недели

            System.out.println(day + " " + month + " " + year);  //выводим дату на печать
            String dw;
            dw = switch (dayOfWeek) {
                case MONDAY -> "Понедельник";
                case TUESDAY -> "Вторник";
                case WEDNESDAY -> "Среда";
                case THURSDAY -> "Четверг";
                case FRIDAY -> "Пятница";
                case SATURDAY -> "Суббота";
                case SUNDAY -> "Воскресенье";
            };

            System.out.println("День недели - " + dw);

            Holiday[] dayHolidays;
            dayHolidays = getMonthHolidays(month); //определяем перечень праздников для данного месяца

            dayHolidays = Stream.of(dayHolidays) //оставляем только праздники, соответствующие заданному дню
                    .filter(h -> checkHoliday(day, month, month.length(isLeap(year)), dayOfWeek, new Holiday[]{h}))
                    .toArray(Holiday[]::new);

            //выводим на печать информацию о праздниках
            if (dayHolidays.length > 0) {
                System.out.println("Праздники в этот день:");
                System.out.println(Arrays.toString(dayHolidays));
            } else {
                System.out.println("В этот день праздников нет.");
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    //вывести инфо о дате
//общий ход расчета взят отсюда https://www.wikihow.com/Calculate-the-Day-of-the-Week
    private static DayOfWeek calcDayOfWeek(int day, Month month, int year) {

        //определяем индекс месяца
        int monthIndex;
        monthIndex = calcMonthIndex(month);

        //учет високосности года
        int leapYearIndex;
        leapYearIndex = calcLeapYearIndex(month, year);

        //учет столетия
        int centuryIndex;
        centuryIndex = calcCenturyIndex(year);

        //учет года
        int yearIndex;
        yearIndex = calcYearIndex(year);

        //складываем день и индекс месяца
        int x;
        x = day + monthIndex;

        //от суммы дня и индекса месяца отнимаем наибольшее делимое числа 7 меньшее этой суммы
        int y;
        y = x - highestMultiple(x, 7);

        //суммируем индекс года, индекс високосного года, индекс столетия, результат обработки индекса месяца и дня
        int p;
        p = yearIndex + leapYearIndex + centuryIndex + y;

        //от этйо суммы отнимаем наибольшее делимое числа 7 меньшее этой суммы
        int dayIndex;
        dayIndex = p - highestMultiple(p, 7);

        //в зависимости от полученного индекса определяем день
        return switch (dayIndex) {
            case 1 -> DayOfWeek.SUNDAY;
            case 2 -> DayOfWeek.MONDAY;
            case 3 -> DayOfWeek.TUESDAY;
            case 4 -> DayOfWeek.WEDNESDAY;
            case 5 -> DayOfWeek.THURSDAY;
            case 6 -> DayOfWeek.FRIDAY;
            case 0, 7 -> DayOfWeek.SATURDAY;
            default -> throw new IllegalStateException("Unexpected value: " + dayIndex);
        };
    }

    /*проверка является ли год високосным (високосным является год,
    который делится без остатка на 4, но не делится на 100, исключение делящиеся на 400 года)*/
    private static boolean isLeap(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    /*Рассчитваем индекс висооксности года.
    // Если год високосный и число лежит в пределах января-февраля, возрвщаем -1, иначе 0.*/
    private static int calcLeapYearIndex(Month month, int year) {
        return isLeap(year) && (month == Month.JANUARY || month == Month.FEBRUARY) ? -1 : 0;
    }

    //рассчитаем индекс столетия
    private static int calcCenturyIndex(int year) {

        int c;
        c = year / 100; //определяем цифры столетия (для 2000-х это 20, для 1900-х- 19)

        /*От столетия отнимаем наибольшее делимое числа 4 меньшее цифры столетия.
        Полученное значение умножаем на 5 и определяем остаток последнего при делении на 7.*/
        return (c - highestMultiple(c, 4)) * 5 % 7;
    }

    //расситаем индекс месяца (прсот отабличные значения)
    private static int calcMonthIndex(Month month) {
        return switch (month) {
            case JANUARY, OCTOBER -> 0;
            case MAY -> 1;
            case AUGUST -> 2;
            case FEBRUARY, MARCH, NOVEMBER -> 3;
            case JUNE -> 4;
            case DECEMBER, SEPTEMBER -> 5;
            case APRIL, JULY -> 6;
        };
    }

    //рассчитаем индес года
    private static int calcYearIndex(int year) {

        //от года отнимаем наибольшее делимое числа 100 меньшее года
        int y1;
        y1 = year - highestMultiple(year, 100);

        //от полученного числа отнимаем наибольшее делимое числа 28 меньшее этого числа
        int y2;
        y2 = y1 - highestMultiple(y1, 28);

        return y2 + y1 / 4;
    }

    //метод для расчета наибольшего делимое числа меньшее исходного числа
    private static int highestMultiple(int a, int b) {
        return a / b * b;
    }

    //вывести месяц на печать
    public void printMonth(Month month, int year) throws IllegalArgumentException {

        if (year >= 0) {
            //определяем списков праздников в этом месяце
            Holiday[] monthHolidays;
            monthHolidays = getMonthHolidays(month);

            //переводим имя месяца на русский
            String monthName;
            monthName = switch (month) {
                case JANUARY -> "Январь";
                case FEBRUARY -> "Февраль";
                case MARCH -> "Март";
                case APRIL -> "Апрель";
                case MAY -> "Май";
                case JUNE -> "Июнь";
                case JULY -> "Июль";
                case AUGUST -> "Август";
                case SEPTEMBER -> "Сентябрь";
                case OCTOBER -> "Октябрь";
                case NOVEMBER -> "Ноябрь";
                case DECEMBER -> "Декабрь";
            };

            //определяем високосность года
            boolean isLeap;
            isLeap = isLeap(year);

            //определяем количество дней в месяце
            int daysInMonth;
            daysInMonth = month.length(isLeap);

            //определяем день недели в первое число месяца
            DayOfWeek firstDayOfMonth;
            firstDayOfMonth = calcDayOfWeek(1, month, year);

            System.out.println(monthName + " " + year);
            System.out.println("ПН ВТ СР ЧТ ПТ СБ ВС");

            //задаем переменные для окрашивания праздничных дней в красный
            final String ANSI_RED = "\u001B[31m";
            final String ANSI_RESET = "\u001B[0m";

            //создаем отступ до первого число, если оно не в понедельник
            for (int i = 0; i < firstDayOfMonth.getValue() - 1; i++) {
                System.out.print("   ");
            }

            //обходим все дни месяца
            for (int i = 1, j = firstDayOfMonth.getValue(); i <= daysInMonth; i++, j++) {

//проверяем является ли день праздничным, если да - красим в красный
                if (checkHoliday(i, month, daysInMonth, DayOfWeek.of(j), monthHolidays)) {
                    System.out.print(ANSI_RED);
                    System.out.print(i);
                    System.out.print(ANSI_RESET);
                } else { //если нет- просто печатаем
                    System.out.print(i);
                }

                //далее реализован переход на следующую строку в конце недели
                if (j % 7 == 0) {
                    System.out.print("\n");
                    j = 0;
                } else if (i > 9) { //здесь учитываются разные интервалы между двухзначными и однозначными числами
                    System.out.print(" ");
                } else {
                    System.out.print("  ");
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    //вывести год на печать (просто выводим на печать все 12 месяцев с помощью цикла)
    public void printYear(int year) {
        for (int i = 1; i < 13; i++) {
            printMonth(Month.of(i), year);
            System.out.println("\n");
        }
    }

    //проверяем является ли день праздником
    private boolean checkHoliday(int day, Month month, int daysInMonth, DayOfWeek dayOfWeek, Holiday[] holidays) {

        for (Holiday holiday : holidays) {//обходим список праздников

            if (holiday instanceof FixedDateHoliday //если рассматривается праздник с фиксированной датой
                    && ((FixedDateHoliday) holiday).getDay() == day //и совпадает день
                    && holiday.getMonth().equals(month)) { //и месяц

                return true; //возвращаем true
            }

            //если речь о празднике с плавающей датой, сиспользуем отдельную функцию
            else if (holiday instanceof FloatDateHoliday &&
                    checkFloatDateHoliday(day, month, daysInMonth, dayOfWeek, (FloatDateHoliday) holiday)) {

                return true; //возвращаем true
            }
        }
        return false;
    }

    //функция для проверки является ли день праздником с плавающей датой
    private boolean checkFloatDateHoliday(int day, Month month, int daysInMonth, DayOfWeek dayOfWeek, FloatDateHoliday holiday) {

        return holiday.getMonth().equals(month) && //если месяц совпадает
                holiday.getDayOfWeek().equals(dayOfWeek) && //и день недели тоже
                ((holiday.getIndex() == 0 && day > daysInMonth - 7) || //и либо это последний день месяца (а он и тербуется)
                        (holiday.getIndex() - 1 <= day / 7 && holiday.getIndex() > day / 7)); //либо это 1,2,3 или 4 дни
        //возвращаем true
    }

    //добавить праздник с фиксированной датой
    public void addHoliday(int day, Month month, String description) throws IllegalArgumentException {
        if (day > 0 && day <= month.length(false) && !description.equals("")) {
            Calendar.Holiday holiday = new Calendar.FixedDateHoliday(day, month, description); //создаем новый объект праздник

            holidays = Arrays.copyOf(holidays, holidays.length + 1); //расширяем массив праздников

            holidays[holidays.length - 1] = holiday; //добавляем туда новый праздник
        } else {
            throw new IllegalArgumentException();
        }
    }

    //добавить праздник с плавающей датой
    public void addHoliday(DayOfWeek dayOfWeek, int index, Month month, String description) throws IllegalArgumentException {
        if (index > -1 && index < 5 && !description.equals("")) {
            Calendar.Holiday holiday = new Calendar.FloatDateHoliday(dayOfWeek, index, month, description); //создаем новый объект праздник

            holidays = Arrays.copyOf(holidays, holidays.length + 1); //расширяем массив праздников

            holidays[holidays.length - 1] = holiday; //добавляем туда новый праздник
        } else {
            throw new IllegalArgumentException();
        }
    }

    //метод для определния праздников в конкретном месяце
    private Holiday[] getMonthHolidays(Month month) {
        return Stream.of(holidays)
                .filter(h -> h.getMonth().equals(month))
                .toArray(Holiday[]::new);
    }

    //внутренний класс, с помощью объектов которого можно хранить информацию овыходных и праздничных днях.
    abstract class Holiday {

        private Month month; //месяц
        private String description; //описание праздника

        //конструктор
        public Holiday(Month month, String description) {
            this.month = month;
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }

        //геттер
        public Month getMonth() {
            return month;
        }
    }

    //праздник с плавающей датой
    class FloatDateHoliday extends Holiday {

        private DayOfWeek dayOfWeek; //день недели
        private int index; //номер дня (0 - последний день (недели) месяца, 1,2,3,4 - первый .. четвертый соответственно))

        //конструктор
        public FloatDateHoliday(DayOfWeek dayOfWeek, int index, Month month, String description) {
            super(month, description);
            this.dayOfWeek = dayOfWeek;
            this.index = index;
        }

        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        public int getIndex() {
            return index;
        }
    }

    //праздник с фиксированной датой
    class FixedDateHoliday extends Holiday {

        private int day; //день

        //конструктор
        public FixedDateHoliday(int day, Month month, String description) {
            super(month, description);
            this.day = day;
        }

        public int getDay() {
            return day;
        }
    }
}
