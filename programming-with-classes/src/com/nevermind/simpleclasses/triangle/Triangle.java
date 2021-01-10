package com.nevermind.simpleclasses.triangle;

/*7. Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов, вычисления
площади, периметра и точки пересечения медиан.*/

public class Triangle {

    //поля класса
    private final double[] a = new double[2]; //точка A координаты x,y
    private final double[] b = new double[2]; //точка B координаты x,y
    private final double[] c = new double[2]; //точка C координаты x,y

    //конструктор
    public Triangle(double ax, double ay, double bx, double by, double cx, double cy) {
        a[0] = ax;
        a[1] = ay;
        b[0] = bx;
        b[1] = by;
        c[0] = cx;
        c[1] = cy;
    }

    //расстояние между точками.
    private double length(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2));
    }

    //находим площадь треугольника по формуле Герона
    public double area() {
        double p;
        p = perimeter();
        double hp = p / 2;
        return Math.sqrt(hp * (hp - length(a, b)) * (hp - length(b, c)) * (hp - length(c, a)));
    }

    //Периметр треугольника равен сумме  длин всех  сторон
    public double perimeter() {
        return length(a, b) + length(b, c) + length(c, a);
    }

    //определим координаты точки пересечения медиан (среднее трех точек)
    public double[] medianIntersectionPoint() {
        double x;
        x = a[0] + b[0] + c[0] / 3;
        double y;
        y = a[1] + b[1] + c[1] / 3;
        return new double[]{x, y};
    }
}
