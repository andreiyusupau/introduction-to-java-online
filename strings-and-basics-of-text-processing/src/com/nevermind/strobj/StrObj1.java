package com.nevermind.strobj;

//1. Дан текст (строка). Найдите наибольшее количество подряд идущих пробелов в нем.

public class StrObj1 {

    public static void main(String[] args) {

        //строка с лишними пробелами
        String s = " one  two     three     ";

        System.out.println("Исходная строка: ");
        System.out.println(s);

        int maxSpaces;
        maxSpaces = strObj1(s);

        System.out.println("Количество подряд идущих пробелов: ");
        System.out.println(maxSpaces);
    }

    private static int strObj1(String s) {

        //переменная для хранения максимального количества подряд идущих пробелов
        int max = 0;

        //счетчик пробелов
        int counter = 0;

        for (int i = 0; i < s.length(); i++) {

            /*Если символ является пробелом увеличиваем значение счетчика.
             Если это значение выше текущего максимума - меняем максимум*/
            if (s.charAt(i) == ' ') {
                counter++;
                if (counter > max) {
                    max = counter;
                }
            } else { //если символ не является пробелом обнуляем счетчик
                counter = 0;
            }
        }
        return max;
    }
}
