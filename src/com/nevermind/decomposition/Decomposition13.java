package com.nevermind.decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*13. Два простых числа называются «близнецами», если они отличаются друг от друга на 2 (например, 41 и 43).
    Найти и напечатать все пары «близнецов» из отрезка [n,2n], где n - заданное натуральное число больше 2. Для
    решения задачи использовать декомпозицию.*/

public class Decomposition13 {

    public static void main(String[] args) {

        //вводим n с клавиатуры
        int n;
        n=readN();

        findTwins(n);

    }

    private static void findTwins(int n) {

        //цикл от n до 2n
        for (int i = n; i + 2 <= 2 * n; i++) {

            //проверяем являются ли числа с интервалом 2 простыми
            if (isPrime(i) && isPrime(i + 2)) {
                System.out.println("Числа близнецы " + i + " и " + (i + 2));
            }
        }
    }

    //функция, проверяющая является ли число простым
    private static boolean isPrime(int N) {
        //Переменная хранящая информацию о том, является ли число простым
        boolean flag = true;

        //нет смысла двигаться дальше половины числа N, т.к. на число больше половины N само N точно не может делиться
        for (int i = 2; i <= N / 2; ++i) {
            //если число делить на другое число (не себя и не 1) без остатка , то оно не простое
            if (N % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
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
                System.out.print("Введите число n: ");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не лежит в указанном диапазоне, выводим ошибку, цикл повторяется
                if (n <= 2) {
                    System.err.println("Значение n должно быть больше 2");
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

