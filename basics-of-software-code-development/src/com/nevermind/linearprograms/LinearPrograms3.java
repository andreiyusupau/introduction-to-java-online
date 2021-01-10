package com.nevermind.linearprograms;

//3.Вычислить значение выражения по формуле (все переменные принимают действительные значения):(sin(x) + cos(y)) / (cos(x) - sin(y)) * tan(x * y)

public class LinearPrograms3 {

    public static void main(String[] args) {
        double x = 5.0;
        double y = 3.0;
        double result;
        result = linearPrograms3(x, y);
        System.out.println("При значениях переменных x=" + x + ", y=" + y + " значение выражения составит " + result);
    }

    /*Для переменных принимаем тип double, т.к. все числа в формуле должны быть вещественными.
    Предположим, что x, y - вводятся в радианах.Вычисления произведем с помощью функций бибилиотеки Math*/
    public static double linearPrograms3(double x, double y) {
        return (Math.sin(x) + Math.cos(y)) / (Math.cos(x) - Math.sin(y)) * Math.tan(x * y);
    }

}
