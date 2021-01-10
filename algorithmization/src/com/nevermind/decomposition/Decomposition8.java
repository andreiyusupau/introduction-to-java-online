package com.nevermind.decomposition;

    /*8. Задан массив D. Определить следующие суммы: D[1] + D[2] + D[3]; D[3] + D[4] + D[5]; D[4] +D[5] +D[6].
   Пояснение. Составить метод(методы) для вычисления суммы трех последовательно расположенных элементов
   массива с номерами от k до m.
   */

import java.util.Arrays;

public class Decomposition8 {

    public static void main(String[] args) {

        //Массив D
        int[] D = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("Массив D: "+ Arrays.toString(D));

        int sum123;
        sum123 = arrayPartSum(D, 1, 3);
        System.out.println("Сумма элементов массива D[1] + D[2] + D[3] равна " + sum123);

        int sum345;
        sum345 = arrayPartSum(D, 3, 5);
        System.out.println("Сумма элементов массива D[3] + D[4] + D[5] равна " + sum345);

        int sum456;
        sum456 = arrayPartSum(D, 4, 6);
        System.out.println("Сумма элементов массива D[4] + D[5] + D[6] равна " + sum456);
    }

    //функция для определения суммы элементов массива (при задании параметров m и k) согласно заданию
    private static int arrayPartSum(int[] D, int k, int m) {
        //убеждаемся , что параметры заданы верно
        if (k >= 0 && m - k == 2 && m < D.length) {

            //вычисляем сумму с помощью метода getSum
            return getSum(D[k], D[k + 1], D[m]);
        } else {
            System.out.println("Входные параметры заданы неверно");
            return -1;
        }

    }

    /*так как m всегда равен k+2 (в задании указано только вычисление суммы трех последовательно расположенных элементов)
     имеет смысл применение такого метода с меньшим количеством входных параметров*/
    private static int arrayPartSum(int[] D, int k) {

        //вычисляем m
        int m;
        m = k + 2;
        //убеждаемся , что входные параметры позволяют произвести вычисления
        if (k >= 0 && m < D.length) {

            //вычисляем сумму с помощью метода getSum
            return getSum(D[k], D[k + 1], D[m]);
        } else {
            System.out.println("Входные параметры заданы неверно");
            return -1;
        }

    }

    //функция для суммирования элементов массива
    private static int getSum(int a, int b, int c) {
        return a + b + c;
    }

}

