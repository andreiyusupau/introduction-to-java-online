package com.nevermind.twodimarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

     /*8. В числовой матрице поменять местами два столбца любых столбца, т. е. все элементы одного столбца поставить
на соответствующие им позиции другого, а его элементы второго переместить в первый. Номера столбцов вводит
пользователь с клавиатуры.*/

public class TwoDimArrays8 {

    public static void main(String[] args) {

        int[][] a = new int[][]{{11, 52, 13, 14, 15, 13},
                {21, 22, 23, 24, 25, 15},
                {31, 32, 333, 34, 35, 56},
                {31, 332, 33, 344, 355, 87},
                {31, 532, 33, 345, 35, 67}};

        System.out.println("Исходная матрица:");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        twoDimArrays8(a);

        System.out.println("Матрица после замены столбцов:");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }
    }

    private static void twoDimArrays8(int[][] a) {

        System.out.println("Первый столбец");
        int m;
        m = readN(a[0].length);

        System.out.println("Второй столбец");
        int n;
        n = readN(a[0].length);

        //выполняем замену только если это не один и тот же столбец
        if (n != m) {
            int[] temp = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                temp[i] = a[i][m];
                a[i][m] = a[i][n];
                a[i][n] = temp[i];
            }
        }
        else {
            System.out.println("Введенные номера столбцов совпадают. Обмена не будет");
        }
    }


    //функция для ввода N с клавиатуры
    private static int readN(int length) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

//последний столбец матрицы
        int last;
        last = length - 1;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите номер столбца от 0 до " + last+" :");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не лежит в указанном диапазоне, выводим ошибку, цикл повторяется
                if (n < 0 || n > last) {
                    System.err.println("Значение должно лежать в указанном диапазоне");
                }

                //Если введённое n соответсвует всем условиям, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return n;
    }
}

