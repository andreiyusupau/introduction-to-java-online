package com.nevermind.twodimarrays;

import java.util.Arrays;

/*12. Отсортировать строки матрицы по возрастанию и убыванию значений элементов.*/

public class TwoDimArrays12 {

    public static void main(String[] args) {

        //Матрица
        int[][] a = new int[][]{{11, 52, 13, 14, 15, 13},
                {21, 22, 23, 24, 25, 15},
                {31, 32, 333, 34, 35, 56},
                {31, 332, 33, 344, 355, 87},
                {31, 532, 33, 345, 35, 67}};

        System.out.println("Исходная матрица:");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        //задаем направление сортировки
        boolean ascending = false;

        twoDimArrays12(a, ascending);

        System.out.println("Матрица после сортировки строк " + (ascending ? "по возрастанию :" : "по убыванию :"));

        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }
    }

    private static void twoDimArrays12(int[][] a, boolean ascending) {

        //поочередно передаем все строки матрицы в функцию сортировки
        for (int i = 0; i < a.length; i++)
            bubbleSort(a[i], ascending);

    }

    //применим пузырьковую сортировку
    private static void bubbleSort(int[] x, boolean ascending) {

        //i отслеживает размер уже отсортированой части массива
        for (int i = 0; i < x.length; i++) {

            //j отслеживает текущий элемент и не может заходить в отсортированную часть массива
            for (int j = 1; j < (x.length - i); j++) {

                //по возрастанию
                if (ascending && x[j - 1] > x[j]) {
                    //меняем местами,если элемент j-1 больше элемента j
                    int temp;
                    temp = x[j - 1];
                    x[j - 1] = x[j];
                    x[j] = temp;
                }

                //по убыванию
                else if (!ascending && x[j - 1] < x[j]) {
                    //меняем местами,если элемент j-1 меньше элемента j
                    int temp;
                    temp = x[j - 1];
                    x[j - 1] = x[j];
                    x[j] = temp;
                }
            }
        }
    }
}

