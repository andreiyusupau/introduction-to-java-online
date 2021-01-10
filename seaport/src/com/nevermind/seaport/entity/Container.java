package com.nevermind.seaport.entity;

//Контейнер
public class Container {

    private final String pointOfDeparture; //пункт отправления
    private final String destination; //пункт назначения
    private final String cargo; //тип груза

    //конструктор
    public Container(String pointOfDeparture, String destination, String cargo) {
        this.pointOfDeparture = pointOfDeparture;
        this.destination = destination;
        this.cargo = cargo;
    }

    //геттеры
    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public String getDestination() {
        return destination;
    }

    public String getCargo() {
        return cargo;
    }
}
