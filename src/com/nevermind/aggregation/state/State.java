package com.nevermind.aggregation.state;

import java.util.Arrays;

//3. Создать объект класса Государство, используя классы Область, Район, Город. Методы: вывести на консоль
//столицу, количество областей, площадь, областные центры.

public class State {

    private String name; //название государства
    private City capital; //столица
    private Region[] regions; //области

    //конструктор
    public State(String name, City capital, Region[] regions) {
        this.name = name;
        this.capital = capital;
        this.regions = regions;
    }

    //Вывести на консоль столицу государства
   public void showCapital() {
        System.out.println("Столицей государства является город " + capital.getName());
    }

    //Вывести на консоль количество областей
    public void regionCount() {
        System.out.println("Количество областей - " + regions.length);
    }

    //Вывести на консоль площадь государства
    public  void calcArea() {

        double area = 0;

        for (Region region : regions) {
            for (Area a : region.getAreas()) {
                area += a.getArea();
            }
        }
        System.out.println("Площадь государства: " + area + " км2");
    }

    //Вывести на консоль областные центры
    public void showRegionCenters() {
        System.out.println("Областные центры:");
        for (Region region : regions) {
            System.out.println(region.getRegionCenter().getName());
        }
    }
}

//область
class Region {

    private String name; //название области
    private Area[] areas; //районы
    private City regionCenter; //областной центр

    //конструктор
    public Region(String name, Area[] areas, City regionCenter) {
        this.name = name;
        this.areas = areas;
        this.regionCenter = regionCenter;
    }

    public City getRegionCenter() {
        return regionCenter;
    }

    public void setRegionCenter(City regionCenter) {
        this.regionCenter = regionCenter;
    }

    public Area[] getAreas() {
        return areas;
    }
}

//район
class Area {

    private String name; //название района
    private City[] cities; //города района
    private double area; //площадь района

    public Area(String name, City[] cities, double area) {
        this.name = name;
        this.cities = cities;
        this.area = area;
    }

    public void addCity(City newCity) {
        cities = Arrays.copyOf(cities, cities.length + 1);
        cities[cities.length - 1] = newCity;
    }

    public double getArea() {
        return area;
    }
}

//город
class City {

    private String name;//название города

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}