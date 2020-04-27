package com.nevermind.simpleclasses.airline;

import java.time.DayOfWeek;
import java.time.LocalTime;

/*10. Создать класс Airline, спецификация которого приведена ниже. Определить конструкторы, set- и get- методы
    и метод toString(). Создать второй класс, агрегирующий массив типа Airline, с подходящими конструкторами и
    методами. Задать критерии выбора данных и вывести эти данные на консоль.
    Airline: пункт назначения, номер рейса, тип самолета, время вылета, дни недели.
    Найти и вывести:
    a) список рейсов для заданного пункта назначения;
    b) список рейсов для заданного дня недели;
    c) список рейсов для заданного дня недели, время вылета для которых больше заданного.*/

public class Airline {

    private String destinationPoint; // пункт назначения
    private long flightNumber; //нмоер рейса
    private String planeType; //модель(тип) самолета
    private LocalTime departureTime; //время вылета
    private DayOfWeek dayOfWeek; //день недели

    //конструктор
    public Airline(String destinationPoint, long flightNumber, String planeType, LocalTime departureTime, DayOfWeek dayOfWeek) {
        this.destinationPoint = destinationPoint;
        this.flightNumber = flightNumber;
        this.planeType = planeType;
        this.departureTime = departureTime;
        this.dayOfWeek = dayOfWeek;
    }

//вывод объекта на печать
    @Override
    public String toString() {
        return "Рейс{" +
                " Пункт назначения - " + destinationPoint +
                ", Номер рейса: " + flightNumber +
                ", Тип самолета - " + planeType +
                ", Время вылета - " + departureTime +
                ", День недели - " + translateDay(dayOfWeek) +
                "}\n";
    }

    //метод для конвертации дня недели из объекта в слово на русском языке
    public static String translateDay(DayOfWeek day) {
        return switch (day) {
            case MONDAY -> "понедельник";
            case TUESDAY -> "вторник";
            case WEDNESDAY -> "среда";
            case THURSDAY -> "четверг";
            case FRIDAY -> "пятница";
            case SATURDAY -> "суббота";
            case SUNDAY -> "воскресенье";
        };
    }

    //геттеры и сеттеры
    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


}
