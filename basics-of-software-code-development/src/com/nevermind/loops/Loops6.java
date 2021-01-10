package com.nevermind.loops;

//6. Вывести на экран соответствий между символами и их численными обозначениями в памяти компьютера

public class Loops6 {

    public static void main(String[] args)  {
        loops6();
    }

    public static void loops6()  {
        /*Существует 63536 символов типа char (от 0 до FFFF в шестнадцатеричной системе).
        Для приведения числа int к символу char воспользуемся явным преобразованием типов.*/
        for (int i = 0x0; i <= 0xFFFF; i++) {
            System.out.println("Символ - " + (char)i + " ,число - " + i + ";");
        }
    }


}
