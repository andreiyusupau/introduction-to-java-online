package com.nevermind.aggregation.car;

public class Main {

    public static void main(String[] args) {

        //создаем автомобиль
        Car car = new Car("Ferrari");

        //выводим на печать марку авто
        car.printCarModel();

        //едем
        car.go();

        //заправляемся
        car.refuel();

        //меняем колесо
        car.swapWheel(0, new Wheel());

    }
}
