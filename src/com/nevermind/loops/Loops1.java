package com.nevermind.loops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    /*1. Напишите программу, где пользователь вводит любое целое положительное число. А программа суммирует
         все числа от 1 до введенного пользователем числа.*/

public class Loops1 {

    public static void main(String[] args) {
        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите целое положительное число x:");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной x
                x = Integer.parseInt(br.readLine());

                //Если введённое x не является положительным числом, выводим ошибку, цикл повторяется
                if (x <= 0) {
                    System.err.println("Значение х должно быть больше 0");
                }
                //Если введённое x является положительным числом, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        //с помощью for
        int s1;
        s1 = loops1(x);
        System.out.println("При x=" + x + " значение суммы всех чисел от 1 до x составит " + s1);

        //с паомощью рекурсии
        int s2;
        s2 = loops1rec(x);
        System.out.println("При x=" + x + " значение суммы всех чисел от 1 до x составит " + s2);
    }

    public static int loops1(int x) {

        //создаем переменную sum для накопления окончательного результата
        int sum = 0;

        //предполагаем, что введенное пользователем число также входит в итоговую сумму (в условии это не оговаривается)
        for (int i = 1; i <= x; i++) {
            sum += i;
        }

        return sum;
    }

//Более изящное, на мой взгляд, решение с помощью рекурсии
    public static int loops1rec(int x) {
        return x==1 ? 1 :  x + loops1(x-1);
    }


}
