package com.nevermind.decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

    /*14. Натуральное число, в записи которого n цифр, называется числом Армстронга, если сумма его цифр,
    возведенная в степень n, равна самому числу. Найти все числа Армстронга от 1 до k. Для решения задачи
    использовать декомпозицию.*/

/*В задании неверно определено понятие числа Армстронга. Из Википедии :
Число Армстронга   — натуральное число, которое в данной системе счисления равно сумме своих цифр,
возведённых в степень, равную количеству его цифр. Т.е. в степень возводится не сумма, а сами числа и затем суммируются.*/

public class Decomposition14 {

    public static void main(String[] args) {

        //вводим k с клавиатуры
        int k;
        k=readK();

        armstrong(k);

    }

   private static void armstrong(int k) {

        //перебираем все числа от 1 до k
        for (int i = 1; i <= k; i++){
            //Переменная для харенния количества разрядов в числе.
            // Количество разрядов определяется с помощью функции из предыдущего задания
            int count;
            count=Decomposition10.length(i);

            if (Arrays.stream(Decomposition10.getDigits(i)) //создаем поток цифр числа с помощью метода из предыдущего задания
                    .map(j->(int)Math.pow(j,count)) //каждую цифру возводим в степень равную количеству цифр в числе
                    .sum() == i){ //суммируем результат и сравниваем с самим числом
                System.out.println("Число Армстронга " + i);
            }
        }
    }

    //функция для ввода k с клавиатуры
    private static int readK() {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите число k: ");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                k = Integer.parseInt(br.readLine());

                //Если введённое k не лежит в указанном диапазоне, выводим ошибку, цикл повторяется
                if (k < 2) {
                    System.err.println("Значение k должно быть больше 1");
                }

                //Если введённое k соответсвует всем условиям, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return k;
    }
}

