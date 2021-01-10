package com.nevermind.twodimarrays;

import java.util.Arrays;

 /*11. Матрицу 10x20 заполнить случайными числами от 0 до 15. Вывести на экран саму матрицу и номера строк, в
    которых число 5 встречается три и более раз.*/

public class TwoDimArrays11 {

    public static void main(String[] args) {

        //Инициализируем матрицу
        int[][] a = new int[10][20];

        twoDimArrays11(a);
    }


    private static void twoDimArrays11(int[][] a) {
//обход строк
        for (int i = 0; i < a.length; i++) {

            //счётчик частоты появляения числа 5
            int counter = 0;

            //обход столбцов
            for (int j = 0; j < a[0].length; j++) {

                //генерируем элемент
                a[i][j] = (int) (Math.random() * 15);

                //если он равен 5 увеличиваем значение счётчика
                if (a[i][j] == 5) {
                    counter++;
                }
            }

            //если значение счётчика больше или равно 3 выводим номер строки
            if (counter >= 3) {
                System.out.println("В строке номер " + i + " число 5 встречается три и более раз");
            }
        }

        System.out.println("Сгенерированная матрица:");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        /*при необходимости выводить матрицу раньше номеров строк можно разделить генерацию элементов матрицы
         и поиск числа 5 в разные циклы*/
    }
}

