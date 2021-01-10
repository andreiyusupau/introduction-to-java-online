package com.nevermind.decomposition;

    /*5. Составить программу, которая в массиве A[N] находит второе по величине число (вывести на печать число,
       которое меньше максимального элемента массива, но больше всех других элементов).
       */

import java.util.Arrays;

public class Decomposition5 {

    public static void main(String[] args) {

        //Задаем массив
        int[] A = new int[]{1, 7, 9, 21, 6, -2, 76, 13, 9};

        System.out.println("Массив A :");
        System.out.println(Arrays.toString(A));

        secondNumber(A);
    }


    private static void secondNumber(int[] A) {

        //проверяем, что массив состоит хотя бы из двух элементов
        if (A.length > 1) {

            //сортируем массив
            Arrays.sort(A);

            //выводим на печать второй по величине элемент
            printSecondNumber(A);
        } else {
            System.out.println("Для выполнения функции в массиве должно быть хотя бы два элемента");
        }
    }

    //функция вывода на печать второго по величине элемента массива
    private static void printSecondNumber(int[] A) {
        System.out.println("Второе по величине число в массиве A : "+A[A.length - 2]);
    }
}

