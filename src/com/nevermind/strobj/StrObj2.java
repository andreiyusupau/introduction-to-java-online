package com.nevermind.strobj;

//2. В строке вставить после каждого символа 'a' символ 'b'

public class StrObj2 {

    public static void main(String[] args) {

        //строка
        String s = "a aa aa asd wae fdga 1a1 a,2";

        System.out.println("Исходная строка: ");
        System.out.println(s);

        String s1;
        s1 = strObj2(s);

        System.out.println("Строка после добавления b после всех a: ");
        System.out.println(s1);
    }

    //для замены символов воспользуемся функцией replaceAll
    private static String strObj2(String s) {
        return s.replaceAll("a", "ab");
    }
}
