package com.nevermind.simpleclasses.test1;

public class Main {

    public static void main(String[] args) {

//Создаем объект класса Test1
        Test1 t1 = new Test1(2, 3);

        //Переопределяем значение переменной a на 4
        t1.setA(4);

        //выводим объект на печать
        t1.print();

        //определяем сумму полей a и b и выводим на печать
        int sum;
        sum = t1.sum();
        System.out.println("Сумма полей a и b равна " + sum);

        //определяем максимальное из полей a и b и выводим на печать
        int max;
        max = t1.max();
        System.out.println("Максимальное значение из a и b равно " + max);

    }
}
