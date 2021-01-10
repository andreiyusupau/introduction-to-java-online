package com.nevermind.strobj;

    /*7. Вводится строка. Требуется удалить из нее повторяющиеся символы и все пробелы. Например, если было введено "abc cde
    def", то должно быть выведено "abcdef".*/

public class StrObj7 {

    public static void main(String[] args) {

        //строка
        String s = "sdjf kjasofj fdns ksdan123 nsjd341 dfnsfjn 123 n3401 n4890  un4 84 84 n19024 ";

        System.out.println("Исходная строка:");
        System.out.println(s);

        String s1;
        s1 = strObj7(s);

        System.out.println("Строка после удаления повторяющихся символов и пробелов:");
        System.out.println(s1);

    }


   private static String strObj7(String s){

        StringBuffer sb= new StringBuffer();

        //решение с помощью возможностей Java 8
        s.replaceAll(" ","") //удаляем все пробелы
        .chars()  //создаем поток символов
        .distinct() // удаляем повторения
        .forEach(c -> sb.append((char) c)); //заносим все элементы в StringBuffer

        return sb.toString();
    }
}
