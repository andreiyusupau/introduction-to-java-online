package com.nevermind.decomposition;

  /*1. Написать метод(методы) для нахождения наибольшего общего делителя и наименьшего общего кратного двух
    натуральных чисел:
    */

public class Decomposition1 {

    public static void main(String[] args) {

        //Задаем натуральные числа a и b
        int a = 120;
        int b = 100;

        int g;
        g = gcd(a, b);
        System.out.println("Наибольший общий делитель чисел " + a + " и " + b + " равен " + g);

        int l;
        l = lcm(a, b);
        System.out.println("Наименьшее общее кратное чисел " + a + " и " + b + " равно " + l);
    }

    //Наибольший общий делитель(применяем алгоритм Евклида)
    public static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    //Наименьшее общее кратное (учитывая, что числа являются натуральными по условию задачи, модуль можно не использовать)
    public static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

}

