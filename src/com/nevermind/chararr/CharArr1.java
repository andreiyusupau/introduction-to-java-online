package com.nevermind.chararr;

import java.util.Arrays;

//1. Дан массив названий переменных в camelCase. Преобразовать названия в snake_case.

public class CharArr1 {

    public static void main(String[] args) {

        //массив названий переменных
        String[] namesCamel = new String[]{"numberOne", "numberTwo", "mrJack", "ohneDich", "ABCD"};

        System.out.println("Массив названий переменных в camelCase " + Arrays.toString(namesCamel));

        String[] namesSnake;
        namesSnake = charArr1(namesCamel);

        System.out.println("Массив названий переменных в snake_case " + Arrays.toString(namesSnake));
    }


    private static String[] charArr1(String[] s) {

        //создаем массив для хранения названий переменных в snake_case
        String[] s2 = new String[s.length];

        //выполянем обход строк из массива
        for (int i = 0; i < s.length; i++) {

            //создаем переменную хранящую значение текущей строки в виде массива символов
            char[] c;
            c = s[i].toCharArray();

            /*Создаем массив для хранения строки в формате snake_case.
            Длина массива выбрана исходя из того, что каждая буква в camelCase может быть большой.*/
            char[] c2 = new char[2 * c.length];
            int j = 0;
            //обходим символы строки. k - итератор строки в camelCase, j - snake_case
            for (int k = 0; k < c.length; k++) {

                //Если какой-то элемент в camelCase написан с большой буквы

                if (Character.isUpperCase(c[k])) {
                    if (k == 0) { // особое условие , если переменная начинается с большой буквы
                        c2[j] = Character.toLowerCase(c[k]);
                        j++;
                    } else { // иначе добавляем в строку snake_case "_" и записываем символ в нижнем регистре*/
                        c2[j] = '_';
                        c2[j + 1] = Character.toLowerCase(c[k]);
                        j += 2;
                    }
                } else {   //иначе просто присваиваем символа
                    c2[j] = c[k];
                    j++;
                }
            }

            //формируем из массива символов строку и заносим в массив строк, символьный массив обрезаем по текущему значению счетчика j
            s2[i] = String.valueOf(c2, 0, j);
        }
        return s2;
    }
}
