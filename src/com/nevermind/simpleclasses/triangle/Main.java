package com.nevermind.simpleclasses.triangle;

public class Main {

    public static void main(String[] args) {
//Создаем треугольник
        Triangle tr = new Triangle(2, 2, 5, 8, 7, 4);

        double area;
        area = tr.area();
        System.out.println("Площадь треугольника равна " + area);

        double perimeter;
        perimeter = tr.perimeter();
        System.out.println("Периметр треугольника равен " + perimeter);

        double[] medIntPoint;
        medIntPoint = tr.medianIntersectionPoint();
        System.out.println("Точка пересечения медиан имеет координаты (" + medIntPoint[0] + "," + medIntPoint[1] + ")");

    }
}
