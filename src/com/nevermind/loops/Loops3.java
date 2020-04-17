package com.nevermind.loops;

//3. Найти сумму квадратов первых ста чисел.

public class Loops3 {

    public static void main(String[] args) {
        //с помощью for
        int sum1;
        sum1 = loops3(100);
        System.out.println("Сумма квадратов первых 100 чисел составит " + sum1);

        //с помощью рекурсии
        int sum2;
        sum2 = loops3rec(100);
        System.out.println("Сумма квадратов первых 100 чисел составит " + sum2);

    }

    public static int loops3(int n) {
        //переменная для хранения суммы
        int sum = 0;
        //Цикл от 1 до n (в данном случае это будет n=100) включительно с шагом 1
        for (int i = 1; i <= n; i++) {
            //Math.pow не используется для ускорения
            sum += i * i;
        }
        return sum;
    }

    //также вариант решения с применением рекурсии
    public static int loops3rec(int x) {
        return x == 1 ? 1 : x * x + loops3rec(x - 1);
    }

}
