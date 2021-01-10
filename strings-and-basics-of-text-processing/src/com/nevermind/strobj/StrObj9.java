package com.nevermind.strobj;

/*9. Посчитать количество строчных (маленьких) и прописных (больших) букв в введенной строке. Учитывать только английские
   буквы.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrObj9 {

    public static void main(String[] args) {

        //строка
        String s;
        s = readS();

        //переменная для хранения количества букв в верхнем регистре
        long up;
        up=upperCount(s);

        //переменная для хранения количества букв в нижнем регистре
        long low;
        low=lowerCount(s);

        System.out.println("Английских букв в верхнем регистре - " + up + ", в нижнем - " + low);

    }


//подсчет английских букв в верхнем регистре
    private static long upperCount(String s) {
        return  s.chars() //создаем поток
                .filter(c -> (c > 64 && c < 91)) //фильтруем по критерию выше
                .count();  //считаем полученные символы
    }

    //подсчет английских букв в нижнем регистре
    private static long lowerCount(String s) {
        return  s.chars() //создаем поток
                .filter(c -> (c > 96 && c < 123)) //фильтруем по критерию выше
                .count(); //считаем полученные символы
    }

    //функция для ввода строки с клавиатуры
    private static String readS() {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод redLine выбрасывает исключение IOException, применяем конструкцию try-catch*/

            try {
                System.out.print("Введите строку: ");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                s = br.readLine();

                //если строка пустая , повторяем ввод
                if (s.length() == 0) {
                    System.out.println("Введите хоть что-нибудь!");
                }
                //Если введённое s соответсвует всем условиям, выходим из цикла
                else {
                    break;
                }
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return s;
    }
}
