package com.nevermind.aggregation.state;

public class Main {

    public static void main(String[] args) {

        //создаем города
        City minsk=new City("Минск");
        City nesvizsh=new City("Несвиж");
        City snov=new City("Снов");
        City grodno=new City("Гродно");
        City volkovyssk=new City("Волковысск");
        City lida=new City("Лида");

        //создаем районы
        Area minskArea= new Area("г.Минск",new City[]{minsk},42);
        Area nesvizshArea=new Area("Несвижский район",new City[]{nesvizsh,snov},35);
        Area grodnoArea =new Area("Гродно",new City[]{grodno},25);
        Area volkovysskArea =new Area("Волковысский район",new City[]{volkovyssk},25);
        Area lidaArea =new Area("Лидский район",new City[]{lida},11);

        //создаем области
        Region minskRegion = new Region("Минская область",new Area[]{minskArea,nesvizshArea},minsk);
        Region grodnoRegion= new Region("Гродненская область",new Area[]{grodnoArea,volkovysskArea,lidaArea},grodno);

        //создаем государство
        State belarus= new State("Республика Беларусь",minsk,new Region[]{minskRegion,grodnoRegion});

        belarus.calcArea();   //выводим на консоль площадь государства

        belarus.regionCount(); //выводим на консоль число областей
        belarus.showCapital(); //выводим на консоль столицу государства
        belarus.showRegionCenters(); //выводим на консоль областные центры
    }
}
