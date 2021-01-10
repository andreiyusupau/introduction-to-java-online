package com.nevermind.decomposition;

import java.util.Arrays;
import java.util.stream.IntStream;

   /*12. Даны натуральные числа К и N. Написать метод(методы) формирования массива А, элементами которого
    являются числа, сумма цифр которых равна К и которые не больше N.*/

public class Decomposition12 {

    public static void main(String[] args) {
        //задаем числа K и N
        int K = 25;
        int N = 1000;

        //формируем массив A
        int[] A;
        A = formArray(K, N);

        System.out.println("Числа, у которых сумма цифр равна " + K + " и которые не больше " + N + " : " + Arrays.toString(A));
    }

    private static int[] formArray(int K, int N) {
        return IntStream.rangeClosed(1, N) // создаем поток чисел от 1 до N
                .filter(i -> sumDigits(i) == K) // оставляем только числа удовлетворяющие условию (сумма цифр равна K)
                .toArray(); // результат заносим в массив
    }

    //найти сумму цифр в числе. Используем метод getDigits из предыдущего задания
    static int sumDigits(int N) {
        return Arrays.stream(Decomposition10.getDigits(N)).sum();
    }
}

