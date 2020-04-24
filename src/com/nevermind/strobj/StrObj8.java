package com.nevermind.strobj;

    /*8. Вводится строка слов, разделенных пробелами. Найти самое длинное слово и вывести его на экран. Случай, когда самых
    длинных слов может быть несколько, не обрабатывать.*/

public class StrObj8 {

    public static void main(String[] args) {

        //строка
        String s = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore" +
                " et dolore magna aliqua";

        System.out.println("Исходная строка:");
        System.out.println(s);

        String s1;
        s1 = strObj8(s);

        System.out.println("Самое длинное слово в строке:");
        System.out.println(s1);
    }

   private static String strObj8(String s){

        //создаем переменную для хранения самого длинного слова
        String longestWord= "";

        //создаем переменную для хранения длины самого длинного слова
        int maxLength =0;

        //создаем массив слов, испольщзуя пробел в качестве разделителя
        String [] words= s.split(" ");

        //выполняем обход массива слов
        for (String word : words ) {

            //если длина текущего слова больше длины максимальной, обновляем слово с максимальной длиной и переменную максимальной длины
            if(word.length()> maxLength){
                longestWord=word;
                maxLength =word.length();}
        }

        return longestWord;
    }

}
