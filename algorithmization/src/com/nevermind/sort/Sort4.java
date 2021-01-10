package com.nevermind.sort;

import java.util.Arrays;

  /*4. Сортировка обменами. Дана последовательность чисел
    a1  a2  an
    .Требуется переставить числа в
    порядке возрастания. Для этого сравниваются два соседних числа
    i i+1 a и a
    . Если
    ai  ai+1
    , то делается
    перестановка. Так продолжается до тех пор, пока все элементы не станут расположены в порядке возрастания.
    Составить алгоритм сортировки, подсчитывая при этом количества перестановок.
    */


public class Sort4 {

    public static void main(String[] args) {

        //создаем массив а
        int[] a = new int[]{1, 2, 3, 19, 20, -1, 0, 22, -34, 0};

        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(a));

        //переменная для подсчёта количества обменов
        int count;
        count = sort4(a);

        System.out.println("Массив после сортировки по возрастанию(количество обменов: " + count + " ): ");
        System.out.println(Arrays.toString(a));
    }

    private static int sort4(int[] a) {
        //создаем переменную для подсчета количества обменов
        int counter = 0;

        //i отражает границу отсортированной части массива
        for (int i = 0; i < a.length - 1; i++) {

            //обход массива
            for (int j = 0; j < a.length - i - 1; j++)

                //если левый элемент больше правого, смещаем его вправо. Увеличиваем значение счетчика на 1
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    counter++;
                }
        }
        return counter;
    }
}
