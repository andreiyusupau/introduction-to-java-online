package com.nevermind.twodimarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//6. Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):
/*      {1   1   1  ... 1   1   1}
        {0   1   1  ... 1   1   0}
        {0   0   1  ... 1   0   0}
        {........................}
        {0   1   1  ... 1   1   0}
        {1   1   1  ... 1   1   1}*/

public class TwoDimArrays6 {

    public static void main(String[] args) {

        //задаем n с помощью ввода с клавиатуры
        int n;
        n = readN();

        int[][] a;
        a = twoDimArrays6(n);

        //выводим на печать итоговый массив
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }
    }

    private static int[][] twoDimArrays6(int n) {
        //создаем массив для хранения чисел
        int[][] a = new int[n][n];

        //Обходим строки
        for (int i = 0; i < n; i++) {

            //обходим столбцы
            for (int j = 0; j < n; j++) {

                //при выполнении одного из условий ставим в ячейку 0, иначе 1. Применяем тернарные операторы
                a[i][j] = j < i || j >= n - i ? 0 : 1;
            }
        }
        return a;
    }

    //функция для ввода n с клавиатуры
    private static int readN() {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите целое положительное чётное число n:");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не является положительным числом, выводим ошибку, цикл повторяется
                if (n <= 0) {
                    System.err.println("Значение n должно быть больше 0");
                }
                //Если введённое n не является чётным числом, выводим ошибку, цикл повторяется
                else if (n % 2 != 0) {
                    System.err.println("Значение n должно быть чётным");
                }
                //Если введённое n является положительным чётным числом, выходим из цикла
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

