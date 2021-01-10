package com.nevermind.decomposition;

/*11. Написать метод(методы), определяющий, в каком из данных двух чисел больше цифр.*/

public class Decomposition11 {

    public static void main(String[] args) {
        //задаем числа a и b
        int a=1255426537;
        int b=98378549;

        System.out.println("Сравним количество цифр в числах " + a+" и "+ b);

        //создаем переменную для хранения числа с максимальным количеством цифр
        int maxDigits;
        maxDigits=compare(a,b);

        System.out.println("Число с наибольшим количеством цифр " +maxDigits);
    }

    /*Предположим, что речь о целых числах
    Для определения количества цифр в числе, применим метод length из предыдущей задачи
    Метод compare сравнивает количество цифр в числа и возвращает, число с наибольшим количеством*/
    private static int compare(int a, int b) {
        return Decomposition10.length(a) > Decomposition10.length(b) ? a : b;
    }

}

