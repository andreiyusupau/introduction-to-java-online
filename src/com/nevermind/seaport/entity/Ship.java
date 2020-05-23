package com.nevermind.seaport.entity;

import java.util.LinkedList;

//корабль
public class Ship {
   private final int carryingCapacity; //максимальное число перевозимых контейнеров(принимаем, что все контейнеры 40-футовые)
    private final LinkedList<Container> cargo; //список перевозимых грузов. LinkedList для возможности применения операций с очередями и со списками
    private final String pointOfDeparture; //пункт отправления
    private final String destination; //пункт назначения

    //конструктор
    public Ship(int carryingCapacity, LinkedList<Container> cargo, String pointOfDeparture, String destination) {
        this.carryingCapacity = carryingCapacity;
        this.cargo = cargo;
        this.pointOfDeparture = pointOfDeparture;
        this.destination = destination;
    }

    //геттеры
    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public LinkedList<Container> getCargo() {
        return cargo;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public String getDestination() {
        return destination;
    }
}
