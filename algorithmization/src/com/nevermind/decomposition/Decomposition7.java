package com.nevermind.decomposition;

/*7. Написать метод(методы) для вычисления суммы факториалов всех нечетных чисел от 1 до 9.*/


public class Decomposition7 {

    public static void main(String[] args) {

        //переменная для хранения суммы факториалов
        long sum;
        sum= sumFact();

        System.out.println("Сумма факториалов всех нечетных чисел от 1 до 9 составит "+sum);
    }

    private static int sumFact() {

        //создаем переменнную для хранения суммы факториалов
        int sum = 0;

        //обходим все нечетные числа от 1 до 9
        for (int i = 1; i <= 9; i += 2) {

            //суммруем значения их факториалов
            // (неявное приведение long к int, т.к. здесь длина суммы позволяет использовать int)
            sum += factorial(i);
        }

        return sum;
    }

    //функция для определния факториала (рекурсия)
    private static long factorial(int n) {

        //Факториал 1 равен 1, факториал 0 равен 0
        if (n <2) {
            return n;
        }
        //если n>=2 умножаем текущее число n на факториал (n-1)
        else {
        return n * factorial(n - 1);
        }
    }

}

