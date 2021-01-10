package com.nevermind.twodimarrays;

import java.util.Arrays;

/*13. Отсортировать столбцы матрицы по возрастанию и убыванию значений элементов.*/

public class TwoDimArrays13 {

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
        boolean ascending = true;

        twoDimArrays13(a, ascending);

        System.out.println("Матрица после сортировки столбцов " + (ascending ? "по возрастанию :" : "по убыванию :"));

        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }
    }

    //применим пузырьковую сортировку
    private static void twoDimArrays13(int[][] a, boolean ascending) {

        //i выполняет обход столбцов
        for (int i = 0; i < a[0].length; i++) {

            //j отслеживает размер уже отсортированой части столбца
            for (int j = 0; j < a.length; j++) {

                //k отслеживает текущий элемент и не может заходить в отсортированную часть столбца
                for (int k = 1; k < (a.length - j); k++) {

                    //по возрастанию
                    if (ascending && a[k - 1][i] > a[k][i]) {
                        //меняем местами,если элемент k-1 больше элемента k
                        int temp;
                        temp = a[k - 1][i];
                        a[k - 1][i] = a[k][i];
                        a[k][i] = temp;
                    }

                    //по убыванию
                    else if (!ascending && a[k - 1][i] < a[k][i]) {
                        //меняем местами,если элемент k-1 меньше элемента k
                        int temp;
                        temp = a[k - 1][i];
                        a[k - 1][i] = a[k][i];
                        a[k][i] = temp;
                    }
                }
            }
        }
    }
}

