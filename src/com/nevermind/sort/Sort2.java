package com.nevermind.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/*2. Даны две последовательности
    a1  a2  an и b1  b2  bm
    . Образовать из них новую последовательность
    чисел так, чтобы она тоже была неубывающей. Примечание. Дополнительный массив не использовать.*/

public class Sort2 {

    public static void main(String[] args) {

        //создаем массив а
        int[] a = new int[]{1, 2, 3, 19, 20};
        //создаем массив b
        int[] b = new int[]{6, 7, 8, 9, 10};

        System.out.println("Массив a:");
        System.out.println(Arrays.toString(a));

        System.out.println("Массив b:");
        System.out.println(Arrays.toString(b));

        //чтобы не создавать новый массив согласно условию, переприсваиваем массиву a новое значение
        a = sort2(a, b);

        System.out.println("Объединенный массив: ");
        System.out.println(Arrays.toString(a));
    }

    private static int[] sort2(int[] a, int[] b) {
        /*Используем Stream API . С помощью IntStream.of превращаем массивы a и b в потоки.
        Объединяем их методом concat, сортируем методом sort (для получения неубывающей последововательности),
        преобразовываем в массив методом toArray. */
        return IntStream.concat(
                IntStream.of(a), IntStream.of(b))
                .sorted()
                .toArray();
    }
}
