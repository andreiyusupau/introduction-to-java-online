package com.nevermind.decomposition;

import java.util.Arrays;

 /*10. Дано натуральное число N. Написать метод(методы) для формирования массива, элементами которого
       являются цифры числа N.*/



public class Decomposition10 {

    public static void main(String[] args) {
        //задаем число N

        int N = 123456789;

        System.out.println("Число N : " + N);

        //задаем массив  M для хранения цифр N, И наполняем его
        int[] M;
        M = getDigits(N);

        System.out.println("Итоговый массив : " + Arrays.toString(M));
    }

    public static int[] getDigits(int N) {

        //определим количество цифр в числе
        int numberOfDigits = length(N);

        //создадим массив записи этих цифр
        int[] digits = new int[numberOfDigits];

        //итератор i - для обхода цифр, j- для заполнения массива
        for (int i = numberOfDigits, j = 0; i > 0; i--, j++) {

            //рассчитаем количество разрядов на текущей итерации
            int base;
            base = (int) Math.pow(10, i - 1);

            /*при делении дробная часть отпадает за счет перевода в int и остается только самая левая цифра.
            Цифра заносится в массив*/
            digits[j] = N / base;

            //корректируем текущее число, убирая крайнюю левую цифру
            N -= digits[j] * base;
        }
        return digits;
    }

    //метод, определяющий количество цифр в числе
    static int length(int a) {
        return (int) (Math.log10(a) + 1);
    }
}

