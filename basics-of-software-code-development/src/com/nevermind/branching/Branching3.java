package com.nevermind.branching;

//3. Даны три точки А(х1,у1), В(х2,у2) и С(х3,у3). Определить, будут ли они расположены на одной прямой.

public class Branching3 {

    public static void main(String[] args) {
        //Точка А
        int x1 = 5;
        int y1 = 2;
        //Точка B
        int x2 = 0;
        int y2 = 4;
        //Точка C
        int x3 = 0;
        int y3 = 6;

        boolean areInline;
        areInline = branching3(x1, y1, x2, y2, x3, y3);
        System.out.println("Находятся ли точки A(" + x1 + "," + y1 + "), B(" + x2 + "," + y2 + "), C(" + x3 + "," + y3 + "), на одной прямой? Ответ - " + areInline);
    }


    public static boolean branching3(int x1, int y1, int x2, int y2, int x3, int y3) {
        /*Это можно определить с помощью теоремы Герона, которая позволяет найти площадь треугольника,
        создаваемого данными точками, если площадь равна нулю - значит они лежат на одной прямой.*/

        //определяем длины всех сторон треугольника. Для удобства используем вспомогательный метод length
        double a;
        a = length(x1, y1, x2, y2);
        double b;
        b = length(x2, y2, x3, y3);
        double c;
        c = length(x1, y1, x3, y3);

        //определяем полупериметр треугольника
        double p;
        p = (a + b + c) / 2;

        //определяем площадь треугольника
        double s;
        s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        //если площадь больше нуля возвращаем false
        return !(s > 0);
    }

    //вспомогательный метод для определния длины отрезка
    private static double length(int m1, int n1, int m2, int n2) {
        //Применяем теорему Пифагора
        return Math.sqrt(Math.pow(m1 - m2, 2) + Math.pow(n1 - n2, 2));
    }
}
