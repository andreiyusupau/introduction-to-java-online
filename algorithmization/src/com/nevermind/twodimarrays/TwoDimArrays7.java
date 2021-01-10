package com.nevermind.twodimarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

    /*7. Сформировать квадратную матрицу порядка N по правилу:
  A[I,J]=sin((I^2-J^2)/N)
     и подсчитать количество положительных элементов в ней.*/

public class TwoDimArrays7 {

    public static void main(String[] args) {

        //задаем N с помощью ввода с клавиатуры
        int N;
        N = readN();

        int counter;
        counter = twoDimArrays7(N);

        System.out.println("Количество положительных элементов в матрице: " +counter);
    }


    private static int twoDimArrays7(int N) {
        double[][] a = new double[N][N];

        //создаем счётчик положительных элементов
        int counter = 0;

        //обходим строки
        for (int i = 0; i < N; i++) {

            //обходим столбцы
            for (int j = 0; j < N; j++) {

                //присваиваем ячейке значение согласно формуле
                a[i][j] = Math.sin((double) (i * i - j * j) / N);
                if (a[i][j] > 0) {
                    //если получился положительный элемент , то увеличивам значение счётчика на 1
                    counter++;
                }
            }
        }
        //выводим на печать матрицу
        for (double[] x : a) {
            System.out.println(Arrays.toString(x));
        }
        return counter;
    }


    //функция для ввода N с клавиатуры
    private static int readN() {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите целое положительное число N:");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не является положительным числом, выводим ошибку, цикл повторяется
                if (n <= 0) {
                    System.err.println("Значение N должно быть больше 0");
                }

                //Если введённое n является положительным числом, выходим из цикла
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

