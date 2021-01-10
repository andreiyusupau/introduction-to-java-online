package com.nevermind.decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*16. Написать программу, определяющую сумму n - значных чисел, содержащих только нечетные цифры.
    Определить также, сколько четных цифр в найденной сумме. Для решения задачи использовать декомпозицию.*/

public class Decomposition16 {

    public static void main(String[] args) {

        //вводим n с клавиатуры
        // (максимум данной программы - 9-тизначные числа, при необхоимости разрядность можно увеличить изменением типа)
        int n;
        n = readN();

        int sum;
        sum = sumOdds(n);
        System.out.println("Сумма цифр " + n + "-значных чисел, содержащих только нечетные цифры равна " + sum);

        int even;
        even = countEven(sum);
        System.out.println("Количество четных цифр в данной сумме равно " + even);
    }

    private static int sumOdds(int n) {

        int sum = 0;

        //определим первое число
        int firstNum;
        firstNum = (int) Math.pow(10, n - 1);

        for (int i = firstNum; i < firstNum * 10; i++) {

            boolean onlyOdd = true;

            //разбиваем число на цифры
            for (int x : Decomposition10.getDigits(i)) {

                //если хоть одна цифра числа является чётной, прекращаем рассматривать число
                if (isEven(x)) {
                    onlyOdd = false;
                    break;
                }
            }

            //если все цифры в числе нечетные добавляем его к сумме
            if (onlyOdd) {
                sum += i;
            }
        }
        return sum;
    }

    //метод, определяющий ли число чётным
    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static int countEven(int sum) {
        //расчёт количества чётных цифр в числе
        int count = 0;
        for (int x : Decomposition10.getDigits(sum)) {
            if (isEven(x)) {
                count++;
            }
        }
        return count;
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
                if (n < 1 || n > 9) {
                    System.err.println("Значение n должно быть больше 0 и меньше 9");
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

