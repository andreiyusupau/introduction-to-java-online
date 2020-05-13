package com.nevermind.library.util;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuUtil {

    public static String readPass(String question) {
        Console console = System.console();
        if (console == null) {
            System.err.println("Не удается сосздать консоль");
            return null;
        }
        System.out.println(question);
        String pass = "/";
        while (pass.length() < 8 && pass.contains("/")) {
            pass = String.valueOf(console.readPassword());
        }
        return pass;
    }


    //функция для ввода n с клавиатуры
    public static int readN(String question, int min, int max) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print(question);

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не является положительным числом, выводим ошибку, цикл повторяется
                if (n < min || n > max) {
                    System.err.println("Значение должно лежать в пределах от " + min + " до " + max);
                }
                //Если введённое n является подходящим, выходим из цикла
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

    //функция для ввода s с клавиатуры
    public static String readS(String question) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное значение
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод readLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print(question);

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной s
                s = String.valueOf(br.readLine());

                //Если введённое s пустое повторяем цикл
                if (s.equals("")) {
                    System.err.println("Введите хоть что-нибудь.");
                }
                //Если введённое s является подходящим, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");

            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return s;

    }
}
