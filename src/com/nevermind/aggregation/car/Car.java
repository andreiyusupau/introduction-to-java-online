package com.nevermind.aggregation.car;

/*2. Создать объект класса Автомобиль, используя классы Колесо, Двигатель. Методы: ехать, заправляться,
менять колесо, вывести на консоль марку автомобиля.*/

public class Car {

    String carModel; // марка машины
    Engine engine;  //двигатель
    Wheel[] wheels; //колеса

    //конструктор
    public Car(String carModel) {
        this.carModel = carModel;
        engine = new Engine(); //так как мотору в данной задаче никаких характеристик не требуется, создаем его конструктором по умолчанию
        wheels = new Wheel[]{new Wheel(),new Wheel(),new Wheel(),new Wheel()}; //обычно у машин 4 колеса
    }

    //едем
    public void go() {
        engine.go(); //заводим мотор
        for (Wheel wheel : wheels) wheel.go(); //крутим колеса
    }

    //заправка
    public void refuel() {
        System.out.println("[автомобиль заправлен]");
    }

    //замена колеса
    public void swapWheel(int wheelNumber, Wheel wheel) {
        wheels[wheelNumber] = wheel;
        System.out.println("[колесо заменено]");
    }

    //Вывести марку автомобиля на печать
    public void printCarModel() {
        System.out.println("Марка автомобиля " + carModel);
    }
}

//колесо
class Wheel {

    //колесо может вращаться издавая характерный звук
    public void go() {
        System.out.println("Вжжжж [колесо крутится]");
    }
}

class Engine {
    //двигатель может работать издавая характерный звук
    void go() {
        System.out.println("Врум-врум [двигатель работает]");
    }
}