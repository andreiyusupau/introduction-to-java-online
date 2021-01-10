package com.nevermind.seaport.factory;

import com.nevermind.seaport.entity.Container;
import com.nevermind.seaport.entity.Ship;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

//фабрика кораблей(Runnable для запуска в отдельном потоке)
public class ShipFactory implements Runnable {
    private final int minCarryingCapacity; //минимальная вместимость корабля
    private final int maxCarryingCapacity;//максимальная вместимость корабля
    private final ContainerFactory containerFactory; //ссылка на фабрику контейнеров
    private final String[] ports; //список портов
    private final Semaphore shipSem; //семафор доступа к списку кораблей
    private final LinkedList<Ship> ships; //ссылка на список кораблей

    //конструктор
    public ShipFactory(LinkedList<Ship> ships, Semaphore shipSem, int minCarryingCapacity, int maxCarryingCapacity, ContainerFactory containerFactory, String[] ports) {
        this.ships = ships;
        this.shipSem = shipSem;
        this.minCarryingCapacity = minCarryingCapacity;
        this.maxCarryingCapacity = maxCarryingCapacity;
        this.containerFactory = containerFactory;
        this.ports = ports;
    }

    //переопределяем метод run
    @Override
    public void run() {
        System.out.println("Фабрика кораблей " + toString() + " запущена");
        while (true) {
            createShip(); //создать корабль
        }
    }

    //создать корабль
    public void createShip() {

        //ожидание для того, чтобы вывод работы программы в консоли был читабелен
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //пытаемся включить семафор доступа к списку кораблей
            shipSem.acquire();
            System.out.println("Фабрика кораблей "+ toString()+" включила семафор кораблей");

            //задаем случайную величину вместимости корабля в указанном диапазоне
            int carryingCapacity;
            carryingCapacity = ThreadLocalRandom.current().nextInt(minCarryingCapacity,maxCarryingCapacity+1);

            //задаем случайный порт отправления из списка
            String pointOfDeparture;
            pointOfDeparture = ports[ThreadLocalRandom.current().nextInt(0, ports.length)];

            //задаем случайный порт прибытия из списка
            String destination;
            destination = ports[ThreadLocalRandom.current().nextInt(0, ports.length)];

            //создаем список грузов
            LinkedList<Container> cargo = new LinkedList<>();

            //пока не заполним весь корабль
            while (cargo.size() != carryingCapacity) {
                //генерируем контейнер с помощью фабрики
                Container container;
                container = containerFactory.createContainer(pointOfDeparture);

                //добавляем его в список
                cargo.add(container);
            }

            //добавляем корабль в список кораблей (очередь в порт)
            ships.offer(new Ship(carryingCapacity, cargo, pointOfDeparture, destination));
            System.out.println("НОВЫЙ КОРАБЛЬ. КОРАБЛЕЙ В ОЧЕРЕДИ "+ships.size());

            //освобождаем доступ к списку кораблей
            shipSem.release();
            System.out.println("Фабрика кораблей "+ toString()+" выключила семафор кораблей");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
