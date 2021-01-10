package com.nevermind.decomposition;

import static com.nevermind.decomposition.Decomposition1.gcd;

/*6. Написать метод(методы), проверяющий, являются ли данные три числа взаимно простыми.
 */

public class Decomposition6 {

    public static void main(String[] args) {

        //Задаем три числа
        int a = 9;
        int b = 17;
        int c = 73;

        boolean coprime;
        coprime = areCoprime(a, b, c);

        //выводим результат
        System.out.println("Числа " + a + ", " + b + ", " + c + (coprime ? " являются " : " не являются ") + "взаимно простыми");
    }

    //Если у каждой пары чисел НОД равен 1 значит они являются взаимнопростыми.
    // Для определения НОД применяем метод gcd из первого заданяи данного раздела
    private static boolean areCoprime(int a, int b, int c) {
        return gcd(a, b) == 1 && gcd(b, c) == 1 && gcd(a, c) == 1;
    }
}

