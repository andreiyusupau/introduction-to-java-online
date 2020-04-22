package com.nevermind.decomposition;

  /*3. Вычислить площадь правильного шестиугольника со стороной а, используя метод вычисления площади
       треугольника*/

public class Decomposition3 {

    public static void main(String[] args) {

        //Задаем сторону шестиугольника
        double a = 12.5;

        double area;
        area = hexagonArea(a);

        System.out.println("Площадь правильного шестиугольника со стороной a=" + a + " равна " + area);

    }

    //площадь равностороннего треугольника
    private static double triangleArea(double a) {
        return a * a * Math.sqrt(3.0) / 4.0;
    }

    //Рассмотрим шестиугольник , как шесть равносторонних треугольников.
    // Также проводится проверка на неотрицательность входных данных
    private static double hexagonArea(double a) {
        return a < 0 ? -1 : 6 * triangleArea(a);
    }

}

