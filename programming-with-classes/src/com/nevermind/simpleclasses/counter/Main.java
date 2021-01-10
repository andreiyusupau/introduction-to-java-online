package com.nevermind.simpleclasses.counter;


public class Main {

    public static void main(String[] args) {

        //создаем счетчик
        Counter c = new Counter();

        c.print();

        c.decrement();

        c.print();

        //создаем второй счетчик с входными параметрами
        Counter c1 = new Counter(19, 20, -20);

        c1.print();

        c1.increment();

        c1.print();

        c1.increment();//данное увеличение не пройдет, т.к. счетчик не может превысить свой максимум

        c1.print();
    }
}
