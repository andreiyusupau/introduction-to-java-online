package com.nevermind.dragon.entity;

//сокровище
public class Treasure {

    private String name; //название сокровища
    private String description; //описание сокровища
    private int value; //стоимость сокровища

    public Treasure(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + name + "] - " + value + " талеров - " + description;
    }

    //геттеры
    public int getValue() {
        return value;
    }
}
