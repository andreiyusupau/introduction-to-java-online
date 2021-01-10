package com.nevermind.strobj;

// 6. Из заданной строки получить новую, повторив каждый символ дважды.

public class StrObj6 {

    public static void main(String[] args) {

        //строка
        String s = "abcd1234";

        System.out.println("Исходная строка:");
        System.out.println(s);

        String s1;
        s1 = strObj6(s);

        System.out.println("Строка после повторения каждого символа дважды:");
        System.out.println(s1);

    }


    private static String strObj6(String s){

        //создаем объект StringBuilder
        StringBuilder sb= new StringBuilder();

        //обход исходной строки
       for(int i=0;i<s.length();i++){

           //дважды добавляем каждый символ в StringBuilder
           sb.append(s, i,i+1).append(s,i,i+1);
       }
        /*решение с помощью Stream
        s.chars().forEach(c->sb.append((char) c).append((char) c));*/
        return sb.toString();
    }
}
