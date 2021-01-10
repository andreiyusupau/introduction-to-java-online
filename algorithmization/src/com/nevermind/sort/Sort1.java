package com.nevermind.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/*1. Заданы два одномерных массива с различным количеством элементов и натуральное число k. Объединить их в
    один массив, включив второй массив между k-м и (k+1) - м элементами первого, при этом не используя
    дополнительный массив.*/

public class Sort1 {

    public static void main(String[] args) {

        //создаем массив а
        int[] a = new int[]{1, 2, 3, 4, 5};
        //создаем массив b
        int[] b = new int[]{6, 7, 8, 9, 10};

        System.out.println("Массив a:");
        System.out.println(Arrays.toString(a));

        System.out.println("Массив b:");
        System.out.println(Arrays.toString(b));

        //задаем k
        int k;
        k=readK(a.length);

        //чтобы не создавать новый массив согласно условию, переприсваиваем массиву a новое значение
        a = sort1(a, b, k);

        System.out.println("Массив a после вставки массива b между "+k+" и "+(k+1)+" элементами: ");
        System.out.println(Arrays.toString(a));
    }

    private static int[] sort1(int[] a, int[] b, int k) {
        //1.С помощью Arrays.copyOfRange получаем отдельные части массива a
        //2.Используя IntStreamOf переводим массивы в Stream
        //3.Используя функцию concat объединяем сначала первую часть массива a и массив b
        //4.Затем так же объединяем получившийся массив со второй частью массива a
        //5.Получившуюся конструкцию переводим в массив
        return IntStream.concat(

                IntStream.concat(

                        IntStream.of(Arrays.copyOfRange(a, 0, k + 1)),
                        IntStream.of(b)),

                IntStream.of(Arrays.copyOfRange(a, k + 1, a.length)))

                .toArray();
    }

    //функция для ввода N с клавиатуры
    private static int readK(int kmax) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите число k: ");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной k
                k = Integer.parseInt(br.readLine());

                //Если введённое k не лежит в указанном диапазоне, выводим ошибку, цикл повторяется
                if (k<0||k>=kmax-1) {
                    System.err.println("Значение должно лежать в диапазоне от 0 до "+(kmax-2));
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
