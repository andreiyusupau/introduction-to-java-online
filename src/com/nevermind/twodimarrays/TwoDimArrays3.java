package com.nevermind.twodimarrays;

//3. Дана матрица. Вывести k-ю строку и p-й столбец матрицы.

public class TwoDimArrays3 {

    public static void main(String[] args) {
        //задаем матрицу натуральных чисел
        int[][] a = new int[][]{{11, 52, 13, 14, 15, 13},
                {21, 22, 23, 24, 25, 15},
                {31, 32, 333, 34, 35, 56},
                {31, 332, 33, 344, 355, 87},
                {31, 532, 33, 345, 35, 67}};

        int k = 8;
        int p = 1;

        twoDimArrays3(a, k, p);
    }

    private static void twoDimArrays3(int[][] a, int k, int p) {

        //так как индексы k и p могут выходить за границы массива, заключаем операции над массивом в блок try-catch
        try {
            System.out.println("Строка номер " + k);

            //выводим на печать строку. Меняется индекс столбцов , индекс строки постоянен и равен k.
            for (int j = 0; j < a[k].length; j++) {
                System.out.println(a[k][j]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.err.println("Значение k находится вне границ массива");
        }
        try {
            System.out.println("Столбец номер " + p);

            //выводим на печать столбец. Меняется индекс строк , индекс столбца постоянен и равен p.
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i][p]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.err.println("Значение p находится вне границ массива");
        }
    }
}

