package com.nevermind.twodimarrays;

import java.util.Arrays;

//15. Найдите наибольший элемент матрицы и заменить все нечетные элементы на него.

public class TwoDimArrays15 {

    public static void main(String[] args) {

        //задаем матрицу целых чисел
        int[][] a = new int[][]{{11, 52, -13, 14, 15, 13},
                {21, -22, 23, 24, 25, 15},
                {31, 32, 0, -34, 35, 56},
                {31, 0, 33, -344, 355, 87},
                {31, 532, 0, 345, 35, -67}};

        System.out.println("Исходная матрица: ");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        twoDimArrays15(a);

        System.out.println("Матрица после замены всех нечётных элементов на максимальный: ");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }
    }

    private static void twoDimArrays15(int[][] a) {
        //инициализируем максимальный элемент , присваиваем ему значение нулевой ячейки
        int max = a[0][0];

        //обход строк
        for (int i = 0; i < a.length; i++) {

            //обход столбцов
            for (int j = 0; j < a[0].length; j++) {

                //если элемент больше текущего максимального, переопределяем максимальный
                if (a[i][j] > max) {
                    max = a[i][j];
                }
            }
        }
        System.out.println("Наибольший элемент матрицы: " + max);
        //обход строк
        for (int i = 0; i < a.length; i++) {

            //обход столбцов
            for (int j = 0; j < a[0].length; j++) {

                //все нечётные элементы заменяем на максимальный
                if (a[i][j] % 2 != 0) {
                    a[i][j] = max;
                }
            }
        }
    }
}

