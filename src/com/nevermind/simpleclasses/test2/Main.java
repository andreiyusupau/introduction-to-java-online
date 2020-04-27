package com.nevermind.simpleclasses.test2;

public class Main {

    public static void main(String[] args) {

        //Создаем объект класса Test2 с помощью конструктора с параметрами
        Test2 t2 = new Test2(2, 3);

        //выводим на печать
        t2.print();

        //Создаем объект класса Test2 с помощью конструктора по умолчанию
        Test2 t2e = new Test2();

        //выводим на печать
        t2e.print();

        //Переопределяем значение переменной a на 4
        t2e.setA(4);

        //Переопределяем значение переменной a на 5
        t2e.setB(5);

        //выводим на печать
        t2e.print();
    }
}
