package com.nevermind.loops;

//8. Даны два числа. Определить цифры, входящие в запись как первого так и второго числа.

public class Loops8 {

    public static void main(String[] args) {
        int a = 123535798;
        int b = 552352389;
        loops8(a, b);
    }


    public static void loops8(int a, int b) {
        for (int i = 0; i < 10; i++) {
            //проверяем входит ли цифра i в число a и b
            if (includesDigit(i, a) && includesDigit(i, b)) {
                System.out.println("Оба числа содержат цифру " + i+".");
            }
        }
    }

    //используем вспомогательный метод для определения включает ли число заданную цифру
   private static boolean includesDigit(int inputDigit, int number) {
        /*Преобразуем int  в String.
         String  в поток символов char.
         Ищем совпадение символа преобразованного в int и заданной цифры.
         Если есть хоть одно совпадение, возвращаем true.*/
        return String.valueOf(number).chars().anyMatch(n -> Character.getNumericValue(n) == inputDigit);
    }

}
