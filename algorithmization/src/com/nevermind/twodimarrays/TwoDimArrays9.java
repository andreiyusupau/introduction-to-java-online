package com.nevermind.twodimarrays;

import java.util.Arrays;

    /*9. Задана матрица неотрицательных чисел. Посчитать сумму элементов в каждом столбце. Определить, какой
    столбец содержит максимальную сумму.*/

public class TwoDimArrays9 {

    public static void main(String[] args) {

        //матрица неотрицательных чисел
        double[][] a = new double[][]{{1.1, 5.2, 1.3, 1.4, 1.5, 1.3},
                {2.1, 2.2, 2.3, 2.4, 2.5, 1.5},
                {3.1, 3.2, 3.33, 3.4, 3.5, 5.6},
                {3.1, 3.32, 3.3, 3.44, 3.55, 8.7},
                {3.1, 0, 3.3, 3.45, 3.5, 6.7}};

        System.out.println("Исходная матрица:");
        for (double[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        //переменная для хранения номера столбца с максимальной суммой элементов
        int columnNumber;
        columnNumber = twoDimArrays9(a);

        System.out.println("Номер столбца с макисмальной суммой элементов: " + columnNumber);
    }


    private static int twoDimArrays9(double[][] a) {

        //переменная для хранения максимальной суммы
        double max = Double.NEGATIVE_INFINITY;

        //переменная для хранения номера столба с максимальной суммой
        int columnNumber = 0;

        //обход столбцов
        for (int j = 0; j < a[0].length; j++) {

            //переменная для хранения суммы элементов столбца
            double sum = 0;

            //обход элементов столбца и суммирование их
            for (int i = 0; i < a.length; i++) {
                sum += a[i][j];
            }
            /*если сумма элементов текущего столбца выше максимальной, обновляем значение максимальной
            и номер столбца с максимальной суммой*/
            if (sum > max) {
                max = sum;
                columnNumber = j;
            }
        }
        return columnNumber;
    }

}

