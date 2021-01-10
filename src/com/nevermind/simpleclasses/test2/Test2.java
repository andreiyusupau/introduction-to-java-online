package com.nevermind.simpleclasses.test2;

/*2. Создйте класс Test2 двумя переменными. Добавьте конструктор с входными параметрами. Добавьте
конструктор, инициализирующий члены класса по умолчанию. Добавьте set- и get- методы для полей экземпляра
класса.*/

public class Test2 {

    //поля класса
    private int a;
    private int b;

    //конструктор по умолчанию
    public Test2() {
        a = 0;
        b = 0;
    }

    //конструктор с входными параметрами
    public Test2(int a, int b) {
        this.a = a;
        this.b = b;
    }

    //метод вывода на печать
    public void print() {
        System.out.println("Поле a - " + a + "; поле b - " + b);
    }

    //геттеры и сеттеры
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }


}
