package com.nevermind.onedimarrays;

import java.util.Arrays;

 /*10. Дан целочисленный массив с количеством элементов п. Сжать массив, выбросив из него каждый второй
 элемент (освободившиеся элементы заполнить нулями). Примечание. Дополнительный массив не использовать.*/

public class OneDimArrays10 {

    public static void main(String[] args) {
        //Задаем массив целых чисел
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("Массив до сжатия " + Arrays.toString(a));

        oneDimArrays10(a);

        System.out.println("Массив после сжатия " + Arrays.toString(a));
    }


    private static void oneDimArrays10(int[] a) {

        //Элемент 0 не сдвигается
        for (int i = 1; i < a.length; i++) {
            //Начиная с элемента 1 позицию i займет элемент с индексом 2i
            if (2 * i < a.length) {
                a[i] = a[2 * i];
            }
            //остальную половину массива заполняем нулями
            else {
                a[i] = 0;
            }
        }
    }
}
