package com.nevermind.twodimarrays;

//2. Дана квадратная матрица. Вывести на экран элементы, стоящие на диагонали

//решение для главной диагонали матрицы

public class TwoDimArrays2 {

    public static void main(String[] args) {
    //задаем матрицу натуральных чисел
        int[][] a = new int[][]{{11, 52, 13, 14, 15},
                {21, 22, 23, 24, 25},
                {31, 32, 333, 34, 35},
                {31, 332, 33, 344, 355},
                {31, 532, 33, 345, 35} };

        twoDimArrays2(a);
    }

    private static void twoDimArrays2(int[][] a) {
        //обход рядов матрицы
        for (int i = 0; i < a.length; i++) {

            //обход столбцов матрицы
            for (int j = 0; j < a[0].length; j++) {

                //при равенстве индекса ряда и столбца выводим на печать
                if (i == j) {
                    System.out.println("Элемент, стоящий на диагонали матрицы "+a[i][j]);
                }
            }
        }
    }
}
