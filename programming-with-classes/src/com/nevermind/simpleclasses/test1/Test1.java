package com.nevermind.simpleclasses.test1;

/*1. Создайте класс Test1 двумя переменными. Добавьте метод вывода на экран и методы изменения этих
        переменных. Добавьте метод, который находит сумму значений этих переменных, и метод, который находит
        наибольшее значение из этих двух переменных*/

public class Test1 {

    //поля a и b (доступ private для обеспеченяи инкапсуляции)
    private int a;
    private int b;

    //конструктор
    public Test1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    //метод вывода на печать
    public void print() {
        System.out.println("Поле a - " + a + "; поле b - " + b);
    }

    //метод определния суммы элементов
    public int sum() {
        return a + b;
    }

    //метод определния максимального элемента
    public int max() {
        return Math.max(a, b);
    }

    //сеттеры
    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }
}
