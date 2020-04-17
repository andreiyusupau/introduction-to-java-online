package com.nevermind.onedimarrays;

import java.util.Arrays;

    /*9. В массиве целых чисел с количеством элементов n найти наиболее часто встречающееся число. Если таких
 чисел несколько, то определить наименьшее из них.*/

public class OneDimArrays9 {

    public static void main(String[] args) {
        //Задаем массив целых чисел
        int[] a = new int[]{-1, 0, 8, -65, 0, 76, -457, 5, 324};

        int x;
        x = oneDimArrays9(a);

        System.out.println("В массиве "+Arrays.toString(a)+" наименьшее из наиболее часто встречающихся чисел "+x);
    }

    private static int oneDimArrays9(int[] a) {

        //наиболее часто повторяющееся число
        int mostCommon = a[0];

        //счетчик повторений
        int counter = 0;

        //кол-во повторений у предыдущего числа
        int counterPrev = 0;

        //сравниваем каждое число последовательно со всем массивом и определяем число повторений для него
        for (int x : a) {
            for (int y : a) {
                if (x == y) {
                    counter++;
                }
            }
            //Если количество повторений у нового числа выше, чем у предыдущего, устанавливаем новое наиболее часто встречающееся число.
            //Обновляем счётчик для предыдущего наиболее часто встречающегося числа
            if (counter > counterPrev) {
                mostCommon = x;
                counterPrev = counter;

            }
            //если кол-ва повторений равны, выбираем наименьшее из чисел
            else if (counter == counterPrev && x < mostCommon) {
                mostCommon = x;
            }
            //обнуляем счетчик
            counter = 0;
        }
        return mostCommon;
    }
}
