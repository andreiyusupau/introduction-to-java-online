package com.nevermind.branching;

/* 5. Вычислить значение функции:
        F(x)= x * x - 3 * x + 9; Eсли x <= 3
        F(x)=  1 / (x * x * x + 6); Eсли x > 3 */

public class Branching5 {

    public static void main(String[] args) {
        //в принципе
        double x = 2.0;
        double f;
        f = branching5(x);
        System.out.println("При x=" + x + " значение функции F(x) составит " + f);
    }

    public static double branching5(double x) {

        //Для ускорения вычислений Math.pow не используется

        if (x <= 3)
            return x * x - 3 * x + 9;
        else
            return 1.0 / (x * x * x + 6);
    }
}
