package com.nevermind.linearprograms;

  /*5. Дано натуральное число Т, которое представляет длительность прошедшего времени в секундах.
       Вывести данное значение длительности в часах, минутах и секундах в следующей форме: ННч ММмин SSc.*/

public class LinearPrograms5 {

    public static void main(String[] args) {
        int T = 28886;
        String time;
        time = linearPrograms5(T);
        System.out.println(time);
    }

    public static String linearPrograms5(int T) {

        //для определния количества часов, просто делим время в секундах на 3600 , так как конечный тип int , дробная часть отпадет.
        int hours;
        hours = T / 3600;

        //аналогично остаток от деления на 3600 делим на 60, дробную часть отбрасываем.
        int minutes;
        minutes = T % 3600 / 60;

        //остаток - секунды
        int seconds;
        seconds = T % 3600 % 60;
        //Форматируем строку по шаблону из задания
        return String.format("%02dч %02dмин %02dс", hours, minutes, seconds);
    }
}
