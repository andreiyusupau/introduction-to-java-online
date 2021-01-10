package com.nevermind.onedimarrays;

import java.util.Arrays;

  /*2. Дана последовательность действительных чисел а1 ,а2 ,..., ап. Заменить все ее члены, большие данного Z, этим
    числом. Подсчитать количество замен*/

public class OneDimArrays2 {

    public static void main(String[] args) {
        //задаем массив действительных  чисел
        double[] a = new double[]{-1.0, 0.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        //задаем число Z
        double Z = 4.0;
        System.out.println("Массив до " + Arrays.toString(a));
        //Определяем суммы элементов массива, кратных К
        int count;
        count = oneDimArrays2(a, Z);
        //Так как массив является объектом, он передается в функцию по ссылке и там изменяется

        System.out.println("Массив после " + Arrays.toString(a) + "\nКоличество замен: "  + count);
    }


    private static int oneDimArrays2(double[] a, double Z) {

        //Счетчик замен
        int counter = 0;

        //Проходим все элементы массива
        for (int i = 0; i < a.length; i++) {

            //Если элемент массива больше Z, присваиваем ему значение Z и увеличивает значение счётчика на 1
            if (a[i] > Z) {
                a[i] = Z;
                counter++;
            }
        }
        return counter;
    }
}
