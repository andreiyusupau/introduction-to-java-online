package com.nevermind.strobj;

// 5. Подсчитать, сколько раз среди символов заданной строки встречается буква “а”

public class StrObj5 {

    public static void main(String[] args) {

        //строка
        String s = "aa adfs fdgg asdfas a";

        int count;
        count = strObj5(s);

        System.out.println("Количество раз, которое буква \"а\" встречается в строке \""+s+"\" равно "+count);
    }

  private  static int strObj5(String s){

        //возвращаем разницу в длине между исходной строкой и строкой , где убраны все буквы a
        return s.length() - s.replace("a", "").length();
    }
}
