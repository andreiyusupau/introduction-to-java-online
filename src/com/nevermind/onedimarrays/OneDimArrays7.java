package com.nevermind.onedimarrays;

//7. Даны действительные числа a1, a2.... an. Найти  max( a1 + a2n, a2 + a2n−1, an + an+1)

//Вероятно, даны числа a1...a2n

public class OneDimArrays7 {

    public static void main(String[] args) {
        //Задаем массив действительных чисел
        double[] a = new double[]{-1.0, 0.0, -3.0, 4.0, 5.0, 6.0, -7.0, 8.0, 19.0};

        double max;
        max = oneDimArrays7(a);

        System.out.println("Максимальная сумма пар элементов равна " + max);
    }


    private static double oneDimArrays7(double[] a) {

        //Для выполнения условий задачи требуется массив с чётным количеством элементов. Иначе число n является неопределеннным.
        if (a.length % 2 != 0) {
            System.out.println("Необходим массив с чётным количеством элементов");
            return Double.NaN;
        } else {
            //Присваиваем переменной по поиску максимума минимальное значение
            double max = Double.NEGATIVE_INFINITY;

            /*Обходим массив с двух концов. Итератор i - от начала, j - от конца.
            Цикл останавливается, когда итераторы доходят до середины массива.*/
            for (int i = 0, j = a.length - 1; i < a.length / 2; i++, j--) {

                //Если сумма текущей пары элементов массива больше текущего максимума, вводим новый максимум
                if (a[i] + a[j] > max) {
                    max = a[i] + a[j];
                }
            }
            return max;
        }
    }
}
