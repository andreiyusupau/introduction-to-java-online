package com.nevermind.branching;

//2. Найти max{min(a, b), min(c, d)}.

public class Branching2 {

    public static void main(String[] args) {
        int a = 20;
        int b = 90;
        int c = 90;
        int d = 30;

        int result;
        result = branching2(a, b, c, d);

        System.out.println("Результат вычисления выражения max{min(a, b), min(c, d)} при значениях переменных a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + " будет равен " + result);
    }


    public static int branching2(int a, int b, int c, int d) {

       /*С точки зрения красоты кода, возможно, было бы целесообразно выделить функции min и max
        (хотя они уже есть в библиотеке Math). Но учитывая объем задачи ограничимся одной функцией.*/

        //для компактности применим  тернарные операторы
        int minAB;
        minAB = a < b ? a : b;

        int minCD;
        minCD = c < d ? c : d;

        return minAB > minCD ? minAB : minCD;
    }
}
