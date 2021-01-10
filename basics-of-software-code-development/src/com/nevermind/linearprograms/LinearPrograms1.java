package com.nevermind.linearprograms;

//1. Найдите значение функции: z = ( (a – 3 ) * b / 2) + c

public class LinearPrograms1 {

    public static void main(String[] args) {
        double a = 2.0;
        double b = 3.0;
        double c = 4.0;
        double z;
        z = linearPrograms1(a, b, c);
        System.out.println("При значениях переменных a=" + a + ", b=" + b + ", c=" + c + " значение функции z=" + z);
    }
    /*Так как тип переменных в задании не указан, а в функции производится операция деления,
       принимаем, что все переменные относятся к типу double. При наличии требований к точности
       вычислений возможно применение другого формата данных.*/

    public static double linearPrograms1(double a, double b, double c) {
        return ((a - 3) * b / 2) + c;
    }
}
