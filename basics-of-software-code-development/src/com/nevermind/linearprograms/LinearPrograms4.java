package com.nevermind.linearprograms;

/*4. Дано действительное число R вида nnn.ddd (три цифровых разряда в дробной и целой частях).
Поменять местами дробную и целую части числа и вывести полученное значение числа.*/

public class LinearPrograms4 {

    public static void main(String[] args) {
        double R = 888.999;
        double S;
        S = linearPrograms4(R);
        System.out.println("Если поменять местами целую и дробную части числа R=" + R + ", то получим число S=" + S);
    }

    public static double linearPrograms4(double R) {
        /*Первое слагаемое - отсекаем у nnn дробную часть приведением к int и делением на 1000 получаем 0.nnn.*/

        double tail;
        tail = (int) R / 1000.0;

         /*Второе слагаемое - от nnn.ddd отнимаем nnn ,получаем 0.ddd. Умножаем на 1000 получаем ddd.
        Применение Math.round необходимо, чтобы отсечь погрешность вычислений double в крайних разрядах.*/

        double head;
        head = Math.round((R - (int) R) * 1000);

        return head + tail;
    }
}
//возможна реализация с помощью String.

/*   String Rs;
     Rs = String.valueOf(R);
     return Double.parseDouble(Rs.substring(4) + "." + Rs.substring(0, 3));*/

