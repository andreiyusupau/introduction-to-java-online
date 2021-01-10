package com.nevermind.strobj;

//4. С помощью функции копирования и операции конкатенации составить из частей слова “информатика” слово “торт”

public class StrObj4 {

    public static void main(String[] args) {

        //строка
        String s = "информатика";

        String s1;
        s1 = strObj4(s);

        System.out.println(s1);
    }

    private static String strObj4(String s) {

        //проверяем что входное слово - информатика
        if (s.equals("информатика")) {

            return s.substring(7, 8) //"т"
                    .concat(s.substring(3, 5) //"ор"
                    .concat(s.substring(7, 8))); //"т"

        } else {
            return null;
        }
    }
}
