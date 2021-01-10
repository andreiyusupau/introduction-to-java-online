package com.nevermind.onedimarrays;

import java.util.Arrays;

  /*3. Дан массив действительных чисел, размерность которого N. Подсчитать, сколько в нем отрицательных,
       положительных и нулевых элементов.*/

public class OneDimArrays3 {

    public static void main(String[] args) {
        //задаем массив действительных  чисел
        double[] a = new double[]{-1.0, 0.0, -3.0, 4.0, 5.0, 6.0, -7.0, 8.0, 9.0};

        oneDimArrays3(a);
    }


    private static void oneDimArrays3(double[] a) {

        //Счетчики положительных , отрциательных и нулевых элементов соответственно
        int counterPos = 0;
        int counterNeg = 0;
        int counterZero = 0;

        //Для обхода массива используем цикл foreach. В зависимости от значения элемента увеличиваем значение соответствующего счетчика
        for (double x : a) {
            if (x > 0) {
                counterPos++;
            } else if (x < 0) {
                counterNeg++;
            } else {
                counterZero++;
            }
        }

        //выводим результат
       System.out.println("В массиве "+Arrays.toString(a)+
               " содержится следующее количество элементов: положительных " + counterPos+
               ", отрицательных " + counterNeg+
               ", нулевых " + counterZero);
    }
}
