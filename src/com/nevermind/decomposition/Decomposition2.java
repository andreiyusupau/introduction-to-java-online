package com.nevermind.decomposition;
import static com.nevermind.decomposition.Decomposition1.gcd;

/*2. Написать метод(методы) для нахождения наибольшего общего делителя четырех натуральных чисел.*/

public class Decomposition2 {

    public static void main(String[] args) {

        //Задаем натуральные числа a,b,c,d
        int a = 120;
        int b = 100;
        int c = 130;
        int d = 140;

        int g;
        g = gcdFour(a, b, c, d);

        System.out.println("Наибольший общий делитель чисел " + a + ", " + b + ", " + c + ", " + d + "  равен " + g);

    }

    //используем функцию для нахождения НОД из предыдущего задания, последовательно находя НОД для пар элементов
    private static int gcdFour(int a, int b, int c, int d) {
        return gcd(a, gcd(b, gcd(c, d)));
    }

}

