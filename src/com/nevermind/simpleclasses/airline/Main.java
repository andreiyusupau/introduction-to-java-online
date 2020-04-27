package com.nevermind.simpleclasses.airline;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        //создаем массив рейсов и наполянем его
        AirlineBase ab = new AirlineBase();

        ab.addAirline(new Airline("Дубай", 9428, "Boeing 767", LocalTime.of(11, 43), DayOfWeek.SATURDAY));
        ab.addAirline(new Airline("Дублин", 8548, "Boeing 747", LocalTime.of(18, 21), DayOfWeek.MONDAY));
        ab.addAirline(new Airline("Мехико", 5123, "Airbus A380", LocalTime.of(1, 22), DayOfWeek.FRIDAY));
        ab.addAirline(new Airline("Стокгольм", 3845, "Boeing 767", LocalTime.of(8, 5), DayOfWeek.THURSDAY));
        ab.addAirline(new Airline("Пекин", 1374, "Airbus A380", LocalTime.of(21, 49), DayOfWeek.SATURDAY));

        ab.print();  //выводим массив на печать

        ab.findByDestination("Стокгольм"); //ищем рейсы по пункту назначения

        ab.findByDay(DayOfWeek.SATURDAY); //ищем рейсы по дню недели

        ab.findByDayAndTime(DayOfWeek.MONDAY, LocalTime.of(18, 0)); //ищем рейсы по дню недели и не позднее указанного времени
    }
}
