package com.nevermind.strobj;

//3. Проверить, является ли заданное слово палиндромом

public class StrObj3 {

    public static void main(String[] args) {

        //строка
        String s = "Able was I ere I saw Elba";
        boolean isPalindrome;
        isPalindrome = strObj3(s);

        System.out.println("Является ли выражение \"" + s + "\" палиндромом? " + (isPalindrome ? "Да" : "Нет"));
    }


    private static boolean strObj3(String s) {

        //функция для обхода символов строки. i двигается с начала, j - с конца.
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {

            //приводим символы к нижнему регистру и сравниваем
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}
