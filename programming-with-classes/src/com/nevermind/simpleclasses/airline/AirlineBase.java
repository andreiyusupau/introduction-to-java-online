package com.nevermind.simpleclasses.airline;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

/*10. Создать класс Airline, спецификация которого приведена ниже. Определить конструкторы, set- и get- методы
    и метод toString(). Создать второй класс, агрегирующий массив типа Airline, с подходящими конструкторами и
    методами. Задать критерии выбора данных и вывести эти данные на консоль.
    Airline: пункт назначения, номер рейса, тип самолета, время вылета, дни недели.
    Найти и вывести:
    a) список рейсов для заданного пункта назначения;
    b) список рейсов для заданного дня недели;
    c) список рейсов для заданного дня недели, время вылета для которых больше заданного.*/

public class AirlineBase {

    Airline[] airlines; //массив рейсов

    ///конструктор по умолчанию
    public AirlineBase() {
        airlines = new Airline[0];
    }

    public AirlineBase(Airline[] airlines) {
        this.airlines = airlines;
    }

    //добавление рейса в базу. Копируем текущий массив с добавлением пустого элемента. Вставляем туда новый рейс
    public void addAirline(Airline airline) {
        airlines = Arrays.copyOf(airlines, airlines.length + 1);
        airlines[airlines.length - 1] = airline;
    }

    //метод для удаления рейса
    public void deleteAirline(long flightNumber) {
        airlines = Arrays.stream(airlines) //преобразуем массив в поток
                .filter(a -> !(a.getFlightNumber() == flightNumber)) //если номер рейса совпадает с удаляемым , не возвращаем его
                .toArray(Airline[]::new); //создаем новый массив и присваиваем его значение переменной airlines
    }

    //поиск по пункту назначения
    public void findByDestination(String inputDestinationPoint) {
        System.out.println("Рейсы по направлению "+inputDestinationPoint+" :");
        for (Airline a : airlines) {
            if (a.getDestinationPoint().equals(inputDestinationPoint)) {
                System.out.println(a.getFlightNumber());
            }
        }
    }

    //поиск по дню недели
    public void findByDay(DayOfWeek inputDayOfWeek) {

        System.out.println("Рейсы в день недели - "+Airline.translateDay(inputDayOfWeek)+" :");

        for (Airline a : airlines) {
            if (a.getDayOfWeek().equals(inputDayOfWeek)) {
                System.out.println(a.getFlightNumber());
            }
        }
    }

//поиск по дню недели и дате
//если оба условия выполняются выводим на печать
    public void findByDayAndTime(DayOfWeek inputDayOfWeek, LocalTime inputTime) {

        System.out.println("Рейсы в день недели - "+Airline.translateDay(inputDayOfWeek)+" не ранее "+inputTime+" :");

        for (Airline a : airlines) {
            if (a.getDayOfWeek().equals(inputDayOfWeek) && inputTime.isBefore(a.getDepartureTime())) {
                System.out.println(a.getFlightNumber());
            }
        }
    }
    //метод для вывода списка рейсов
    public void print() {
        System.out.println("Список рейсов:");
        System.out.println(Arrays.toString(airlines));
    }
}
