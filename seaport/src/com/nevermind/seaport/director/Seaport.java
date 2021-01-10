package com.nevermind.seaport.director;

import com.nevermind.seaport.entity.Container;
import com.nevermind.seaport.entity.Ship;
import com.nevermind.seaport.factory.ContainerFactory;
import com.nevermind.seaport.factory.ShipFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//порт
public class Seaport {
    private ArrayList<Dock> docks = new ArrayList<>(); //причалы
    private LinkedList<Ship> ships = new LinkedList<>(); //корабли в очереди в порт
    private LinkedList<Container> containers = new LinkedList<>(); //контейнеры в порту
    private final int capacity = 150; //вместимость порта
    private int currentLoad = 0; //текущая загруженность порта
    private final ShipFactory shipFactory; //фабрика кораблей
    private final Semaphore shipSem = new Semaphore(1); //семафор списка кораблей
    private final Semaphore contSem = new Semaphore(1); //семафор списка контейнеров

    //констуктор
    public Seaport(int dockCount, String[] ports, String[] cargo) {

        //создаем фабрику контейнеров
        ContainerFactory containerFactory;
        containerFactory = new ContainerFactory(ports, cargo);

        //создаем фабрику кораблей
        shipFactory = new ShipFactory(ships, shipSem, 10, 12, containerFactory, ports);

    //создаем причалы
        for (int i = 0; i < dockCount; i++) {
            docks.add(new Dock(this, shipSem, contSem));
        }

        //создаем сервис для создания потоков
        ExecutorService es = Executors.newCachedThreadPool();

        //запускаем поток фабрики кораблей
        es.execute(shipFactory);

        //запускае в работу потоки причалов
        for (Dock dock : docks) {
            es.execute(dock);
        }

    }

    //проверка, что в порту есть место для контейнера
    public boolean checkCapacity() {
        return currentLoad < capacity;
    }

    //добавление контейнера в порт
    public boolean addContainer(Container container) {
        //увеличиваем счетчик контейнеров
        currentLoad++;
        System.out.println("КОНТЕЙНЕР ВЫГРУЖЕН В ПОРТ. ТЕКУЩЕЕ ЧИСЛО КОНТЕЙНЕРОВ - "+currentLoad);
        return containers.add(container); //добавляем контейнер
    }

    //забрать контейнер из порта
    public Container getContainer(String destination) {

        //обходим все контейнеры, ищем контейнер с совпадабющим направлением
        for (int i = 0; i < containers.size(); i++) {
            if (containers.get(i).getDestination().equals(destination)) {
                currentLoad--; //уменьшаем счечтчик
                System.out.println("КОНТЕЙНЕР ЗАГРУЖЕН НА КОРАБЛЬ. ТЕКУЩЕЕ ЧИСЛО КОНТЕЙНЕРОВ - "+currentLoad);
                return containers.remove(i); //удаляем из списка в порту, переносим на корабль
            }
        }
        return null; //если не нашли, возвращаем null
    }

    //достаем из очереди следующий корабль
    public Ship nextShip() {
        return ships.poll();
    }

    //геттер
    public int getShipCount(){
        return ships.size();
    }
}
