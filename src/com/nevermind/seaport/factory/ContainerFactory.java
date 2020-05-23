package com.nevermind.seaport.factory;

import com.nevermind.seaport.entity.Container;

import java.util.concurrent.ThreadLocalRandom;

//фабрика контейнеров
public class ContainerFactory{
    private final String [] ports; //список портов
    private final String [] cargoList; //список видов грузов


    public ContainerFactory(String[] ports, String[] cargoList) {
        this.ports = ports;
        this.cargoList = cargoList;
    }

//создать контейнер
    public Container createContainer(String pointOfDeparture){
        //пункт назначения генерируем из доступных портов, генерируем пока он не будет отличным от пункта отпарвления
       String destination=null;
       while (destination==null||destination.equals(pointOfDeparture)){
           destination=ports[ThreadLocalRandom.current().nextInt(0, ports.length)];
       }

       //генерируем тип груза из доступных
       String cargo;
       cargo=cargoList[ThreadLocalRandom.current().nextInt(0, cargoList.length)];

        return new Container(pointOfDeparture,destination,cargo); //возвращаем новый контейнер с созданными параметрами
    }


}
