package com.nevermind.decomposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*15. Найти все натуральные n-значные числа, цифры в которых образуют строго возрастающую
      последовательность (например, 1234, 5789). Для решения задачи использовать декомпозицию.
      */

public class Decomposition15 {

    public static void main(String[] args) {

        //вводим n с клавиатуры
        int n;
        n = readN();

        increasing(n);
    }

    private static void increasing(int n) {
        /*определим первое и последнее из рассматриваемых чисел с заданным разрядом,
        это нужно чтобы уменьшить количество рассмтриваемых чисел на больших разрядах*/
        int firstNum;
        firstNum = calcFirstNumber(n);

        int lastNum;
        lastNum = calcLastNumber(n);

        //проверяем все числа данной разрядности в указанном диапазаоне
        for (int i = firstNum; i <= lastNum; i++) {

            //переменная для хранения предыдущей цифры для сравнения
            int temp = 0;

            //переменная определяющая является ли последовательность цифр строго возрастающей
            boolean isIncreasing = true;

            //разбиваем число на массив цифр с помощью метода из предыдущей задачи
            for (int x : Decomposition10.getDigits(i)) {
                //если текущая цифра меньше или равна предыдущей, переходим к следующему числу
                if (x <= temp) {
                    isIncreasing = false;
                    break;
                }
                temp = x;
            }
            if (isIncreasing) {
                System.out.println("Цифры числа " + i + " образуют строго возрастающую последовательность");
            }
        }
    }

    //функция для определения первого рассматриваемого числа
    private static int calcFirstNumber(int n) {
        int firstNum = 0;

        //поразрядно собираем число формата 123..
        for (int i = n; i > 0; i--) {
            firstNum += (n - i + 1) * Math.pow(10, i - 1);
        }

        return firstNum;
    }

    //функция для определения последнего рассматриваемого числа - формата ..789.
    //(окажет значительное влияние на скорость расчета больших разрядов)
    private static int calcLastNumber(int n) {
        //определяем базу
        int lastNum;
        lastNum = (int) Math.pow(10, n);

        //от базы последовательно отнимаем значения соответствующие каждому разряду
        for (int i = n; i > 0; i--) {
            lastNum -= (i - 1) * Math.pow(10, i - 1);
        }
     //на финальном этапе отнимаем единицу
        return lastNum - 1;
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
                if (n < 1||n>9) {
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

