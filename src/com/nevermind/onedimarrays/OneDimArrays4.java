package com.nevermind.onedimarrays;

import java.util.Arrays;

/*4. Даны действительные числа а1 ,а2 ,..., аn . Поменять местами наибольший и наименьший элементы.
 */

public class OneDimArrays4 {

    public static void main(String[] args) {
        //Задаем массив действительных  чисел
        double[] a = new double[]{-1.0, 0.0, -3.0, 4.0, 5.0, 6.0, -7.0, 8.0, 9.0};

        System.out.println("Исходный массив" + Arrays.toString(a));

        /*Ищем и меняем местами наибольший и наименьший элементы.
        Массив является объектом, поэтому будет изменен внутри метода и не требует возврата.*/
        oneDimArrays4(a);

        System.out.println("Массив после замены мест наибольшего и наиеньшего элементов " + Arrays.toString(a));
    }


    private static void oneDimArrays4(double[] a) {
        //Устанавливаем переменные отражающие минимальный и максимальный элементы, а также их индексы на нулевой элемент массива
        int imin = 0;
        int imax = 0;

        //Обходим массив начиная с первого элемента
        for (int i = 1; i < a.length; i++) {
            //если элемент массива меньше текущего минимального элемента, записываем индекс нового минимального элемента
            if (a[i] < a[imin]) {
                imin = i;
            }
            //если элемент массива больше текущего максимального элемента, записываем индекс нового максимального элемента
            else if (a[i] > a[imax]) {
                imax = i;
            }
        }

        //меняем элементы местами
        double temp;
        temp = a[imax];
        a[imax] = a[imin];
        a[imin] = temp;
    }
}