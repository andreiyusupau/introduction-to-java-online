package com.nevermind.loops;

  /*7. Для каждого натурального числа в промежутке от m до n вывести все делители, кроме единицы и самого числа.
     m и n вводятся с клавиатуры.   */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loops7 {

    public static void main(String[] args) {
      loops7();
    }


   public static void loops7() {

       //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       int m;
       int n;

       //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число

       //вводим m
       while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

           try {
               System.out.print("Введите натуральное число m:");

               //считываем ввод с клавиатуры и пытаемся присваивоить его переменной m
               m = Integer.parseInt(br.readLine());

               //Если введённое m не является натуральным числом, выводим ошибку, цикл повторяется
               if (m <= 0) {
                   System.err.println("Значение m должно быть больше 0");
               }
               //Если введённое m является натуральным числом, выходим из цикла
               else {
                   break;
               }
           } catch (NumberFormatException nfe) {
               System.err.println("Неправильный формат данных.");
           } catch (IOException ioe) {
               System.err.println("Проблема при вводе данных.");
           }
       }

       //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число

       //вводим n
       while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

           try {
               System.out.print("Введите натуральное число n (должно быть больше m):");

               //считываем ввод с клавиатуры и пытаемся присваивоить его переменной x
               n = Integer.parseInt(br.readLine());

               //Если введённое n меньше m, выводим ошибку, цикл повторяется
               if (n <= m) {
                   System.err.println("Значение n должно быть больше m");
               }
               //Если введённое n больше m, выходим из цикла
               else {
                   break;
               }
           } catch (NumberFormatException nfe) {
               System.err.println("Неправильный формат данных.");
           } catch (IOException ioe) {
               System.err.println("Проблема при вводе данных.");
           }
       }

        boolean isSimple;
        for (int i = m; i <= n; i++) {
            System.out.println("Делители числа " + i + ":");
            isSimple = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    System.out.println(j);
                    isSimple = false;
                }
            }
            if (isSimple) {
                System.out.println("Число " + i + " является простым");
            }
        }
    }


}
