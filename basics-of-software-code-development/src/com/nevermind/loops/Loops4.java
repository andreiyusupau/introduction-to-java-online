package com.nevermind.loops;

import java.math.BigDecimal;

//4. Составить программу нахождения произведения квадратов первых двухсот чисел.

public class Loops4 {

    public static void main(String[] args) {
        //с помощью for
        BigDecimal mul1;
        mul1 = loops4(200);
        System.out.println("Произведение квадратов первых 200 чисел составит " + mul1);

        //с помощью рекурсии
        BigDecimal  mul2;
        mul2 = loops4rec(200);
        System.out.println("Произведение квадратов первых 200 чисел составит " + mul2);

    }

    public static BigDecimal loops4(int n) {
        //переменная для хранения произведения. Объект класса BigDecimal ввиду очень большой длины результата вычислений.
        BigDecimal mul = new BigDecimal(1);
        //Цикл от 1 до n (в данном случае это будет n=200) включительно с шагом 1
        for (int i = 1; i <= n; i++) {
          //используем метод multiply для умножения чисел BigDecimal
            mul=mul.multiply(new BigDecimal(i * i));
        }
        return mul;
    }

    //также вариант решения с применением рекурсии
    public static BigDecimal loops4rec(int x) {
        return x == 1 ? new BigDecimal(1) :  loops4rec(x - 1).multiply(new BigDecimal(x * x));
    }

}
