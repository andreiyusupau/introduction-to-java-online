package com.nevermind.decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

  /*  17. Из заданного числа вычли сумму его цифр. Из результата вновь вычли сумму его цифр и т.д. Сколько таких
      действий надо произвести, чтобы получился нуль? Для решения задачи использовать декомпозицию*/

public class Decomposition17 {

    public static void main(String[] args) {

        //вводим n с клавиатуры
        int n;
        n = readN();

        int count;
        count = numberOfActions(n);
        System.out.println("Количество раз, когда из числа " + n + " вычитается сумма его цифр для получения 0 равно " + count);
    }


    private static int numberOfActions(int n) {

        //счетчик количества вычитаний
        int counter = 0;

        //цикл, в котором от числа отнимается сумма его цифр пока число не станет равным нулю
        //Для определния суммы цифр используем метод из предыдущей задачи
        while (n > 0) {
            n -= Decomposition12.sumDigits(n);
            counter++;
        }
        return counter;
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
                if (n < 0) {
                    System.err.println("Значение n должно быть больше или равно 0");
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

