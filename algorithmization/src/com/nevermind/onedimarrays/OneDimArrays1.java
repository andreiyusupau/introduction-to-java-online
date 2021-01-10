package com.nevermind.onedimarrays;

import java.util.Arrays;

//1. В массив A [N] занесены натуральные числа. Найти сумму тех элементов, которые кратны данному К.

public class OneDimArrays1 {

    public static void main(String[] args) {
//задаем массив натуральных чисел
        int[] A = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        //задаем число K
        int K = 2;

        //Определяем суммы элементов массива, кратных К
        int sum;
        sum = oneDimArrays1(A, K);

        System.out.println("Сумма элементов массива " + Arrays.toString(A) + " , кратных К=" + K + " , составит " + sum);
    }

    private static int oneDimArrays1(int[] A, int K) {
        //задаем переменную для хранения суммы
        int sum = 0;

        //для обхода элементов массива применяем оператор foreach
        for (int a : A) {

            //Если элемент массива кратен К, добавляем его к переменной sum
            if (a % K == 0)
                sum += a;
        }
        return sum;
    }
}
