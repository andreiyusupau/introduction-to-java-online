package com.nevermind.loops;

/* 2. Вычислить значения функции на отрезке [а,b] c шагом h:
y=x, при x>2
y=-x, при x<=2*/

public class Loops2 {

    public static void main(String[] args) {
        int a = 1;
        int b = 5;
        int h = 1;
        loops2(a, b, h);

    }

    public static void loops2(int a, int b, int h) {

//Проверка на правильность входных данных . b должно быть больше a, а интервал [a,b] должен разбиваться шагом h без остатка.
        if (b > a && (a - b) % h == 0) {

            for (int x = a; x <= b; x += h) {

                int y;
                //для упрощения используем тернарный оператор
                y = x > 2 ? x : -x;

                System.out.println("При х=" + x + " значение функции y составит " + y);
            }
        } else {
            System.out.println("Проверьте введенные данные");
        }


    }

}
