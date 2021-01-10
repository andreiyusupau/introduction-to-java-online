package com.nevermind.chararr;

//3. В строке найти количество цифр.

public class CharArr3 {

    public static void main(String[] args) {

        //строка с цифрами и другими символами
        String s = "123fa4fsd5gf 6_7,\n8()9";

        System.out.println("Строка: ");
        System.out.println(s);

        int counter;
        counter=charArr3(s);

        System.out.println("Количество цифр в строке равно "+counter);
    }


    private static int charArr3(String s) {

        //переводим строку в массив символов
        char[] c;
        c= s.toCharArray();

        //создаем счетчик
        int counter = 0;

        //проверяем является ли символ цифрой с помощью метода isDigit класса Character. Если да, увеличиваем значение счетчика
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(c[i]))
                counter++;
        }
        return counter;
    }
}
