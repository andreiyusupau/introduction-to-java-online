package com.nevermind.twodimarrays;

import java.util.Arrays;

/*10. Найти положительные элементы главной диагонали квадратной матрицы.*/

public class TwoDimArrays10 {

    public static void main(String[] args) {

        //матрица
        double[][] a = new double[][]{{-1.1, 5.2, 1.3, 1.4, 1.5},
                {2.1, 2.2, 2.3, 2.4, 2.5},
                {3.1, 3.2, -3.33, 3.4, 3.5},
                {3.1, 3.32, 3.3, 3.44, 3.55},
                {3.1, 0, 3.3, 3.45, 3.5}};

        System.out.println("Исходная матрица:");
        for (double[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        twoDimArrays10(a);
    }


    private static void twoDimArrays10(double[][] a) {

        //проверка матрицы на квадратность
        if (a.length != a[0].length) {
            System.out.println("Матрица не квадратная!");
        } else {

            //обход строк
            for (int i = 0; i < a.length; i++) {

                //обход столбцов
                for (int j = 0; j < a[0].length; j++) {

                    //Если элемент стоит на главной диагонали и паоложителен, ывводим на печать
                    if (i == j && a[i][j] > 0) {
                        System.out.println("Положительный элемент главной диагонали матрицы: " + a[i][j]);
                    }
                }
            }
        }
    }
}

