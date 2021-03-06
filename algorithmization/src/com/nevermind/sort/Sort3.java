package com.nevermind.sort;

import java.util.Arrays;

  /*3. Сортировка выбором. Дана последовательность чисел
    a1  a2  an
    .Требуется переставить элементы так,
    чтобы они были расположены по убыванию. Для этого в массиве, начиная с первого, выбирается наибольший
    элемент и ставится на первое место, а первый - на место наибольшего. Затем, начиная со второго, эта процедура
    повторяется. Написать алгоритм сортировки выбором.
    */

public class Sort3 {

    public static void main(String[] args) {

        //создаем массив а
        int[] a = new int[]{1, 2, 3, 19, 20, -1, 0, 22, -34, 0};

        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(a));

        sort3(a);

        System.out.println("Массив после сортировки по убыванию:");
        System.out.println(Arrays.toString(a));
    }

    private static void sort3(int[] a) {
        //цикл для обхода позиций , на которые будет производиться замена
        for (int i = 0; i < a.length; i++) {
            //начинаем движение по массиву справа от заменяемого элемента
            for (int j = i + 1; j < a.length; j++) {
                //если текущий элемент больше, чем заменяемый элемент, меняем их местами
                if (a[j] > a[i]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }
}
