package com.nevermind.decomposition;

    /*9. Даны числа X, Y, Z, Т — длины сторон четырехугольника. Написать метод(методы) вычисления его площади,
    если угол между сторонами длиной X и Y— прямой.*/

public class Decomposition9 {

    public static void main(String[] args) {

        //задаем длины сторон четырехугольника
        double X = 4;
        double Y = 4;
        double Z = 8;
        double T = 3;

        //Находим площадь
        findArea(X, Y, Z, T);

    }

    /*С учетом условия о налии прямого угла. Будем считать что данный угол лежит в точке (0,0) координатной плоскости,
    Сторона X уходит вправо, сторона Y - вверх.*/

    //функция для вычисления площади четырехугольника
    private static void findArea(double X, double Y, double Z, double T) {

        //Определяем длину диагонали четырехугольника
        double d;
        d = length(X, 0, 0, Y);

        //Рассмотрим ряд исключений, при которых четырехугольник либо не существует, либо не является четырехугольником

        //если диагональ больше суммы сторон Z и T , то Z и T не пересекаются, т.е. четырехугольник не существует
        if (d > Z + T) {
            System.out.println("Четырехугольник не существует. Z и T не пересекаются");
        }

        /*если диагональ больше модуля разности сторон Z и T , то Z и T концы Z и T не могут находится в одной точке,
         т.е. четырехугольник не существует*/
        else if (d < Math.abs(Z - T)) {
            System.out.println("Четырехугольник не существует. Концы Z и T не могут находится в одной точке");
        }

        //если диагональ равна нулю, то четырехугольник является точкой, а его площадь равна 0
        else if (d == 0) {
            System.out.println("Все углы четырехугольника находятся в одной точке. Площадь равна нулю");
        }

        //Если диагональ равна сумме Z+T, то четырехугольник на самом деле является прямогугольным треугольником
        else if (d == Z + T) {
            double a;
            a = 0.5 * X * Y;
            System.out.println("Это прямоугольный треугольник, площадь которого равна " + a);
        }

        //В противном случае предполагаем, что это все-таки четырехугольник.
        else {

            // Начинаем искать точки пересечения Z и T. Их будет 2

            //Дальнейшие методы описаны с использованием данного источника:
            //https://math.stackexchange.com/questions/256100/how-can-i-find-the-points-at-which-two-circles-intersect

            //Находим расстояние от угла четырехугольника ZY до середины линии проведенной между точками пересечения Z и T
            double l;
            l = (Z * Z - T * T + d * d) / (2 * d);

            //Находим половину длины отрезка между тчоками пересечения Z и T
            double h;
            h = Math.sqrt(Z * Z - l * l);

            //Рассмотрим четырехугольник образованный с помощью первой точки
            System.out.println("Первый вариант");

            //Определяем координаты точки пересечения Z и T
            double ZTx1;
            ZTx1 = l / d * X - h / d * Y;

            double ZTy1;
            ZTy1 = -l / d * Y - h / d * X + Y;


            if (ZTx1 == 0) {
                if (ZTy1 >= Y) {
                    double a;
                    a = 0.5 * ZTy1 * X;
                    System.out.println("Это треугольник, его площадь равна " + a);
                } else {
                    System.out.println("Четырехугольник не существует");
                }
            } else if (ZTy1 == 0) {
                if (ZTx1 >= X) {
                    double a;
                    a = 0.5 * ZTx1 * Y;
                    System.out.println("Это треугольник, его площадь равна " + a);
                } else {
                    System.out.println("Четырехугольник не существует");
                }
            } else {
                quadrilateralArea(X, Y, ZTx1, ZTy1);
            }


            //вариант 2
            System.out.println("Второй вариант");
            double ZTx2;
            ZTx2 = l / d * X + h / d * Y;
            double ZTy2;
            ZTy2 = -l / d * Y + h / d * X + Y;


            if (ZTx2 == 0) {
                if (ZTy2 >= Y) {
                    double a;
                    a = 0.5 * ZTy2 * X;
                    System.out.println("Это треугольник, его площадь равна " + a);
                } else {
                    System.out.println("Четырехугольник не существует");
                }
            } else if (ZTy2 == 0) {
                if (ZTx2 >= X) {
                    double a;
                    a = 0.5 * ZTx2 * Y;
                    System.out.println("Это треугольник, его площадь равна " + a);
                } else {
                    System.out.println("Четырехугольник не существует");
                }
            } else {

                quadrilateralArea(X, Y, ZTx2, ZTy2);
            }
        }
    }

    //функция для определения расстояния между точками
    public static double length(double x1, double y1, double x2, double y2) {

        //расстояние между двумя точками определяется как корень из суммы квадратов разностей их координат
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /*Если точка лежит ниже диагонали четырехугольника (между точками (0,Y) и (X,0)),
    а также находится во второй или четвертой части координатной плоскости, то четырехугольник является самопресекающимся*/
    private static boolean isSelfIntersecting(double x1, double y1, double x2, double y2, double xm, double ym) {
        return ((xm < 0 && ym < 0) || (xm > 0 && ym > 0)) ? false : (xm - x1) * (y2 - y1) - (ym - y1) * (x2 - x1) < 0;
    }

    private static void quadrilateralArea(double X, double Y, double ZTx, double ZTy) {

        //Зная координаты точки проверим , является ли полученная фигура четырехугольником
        //Если точка пересечения Z и T лежит на оси Y, а координата ZTy выше Y, получим треугольник.
        //Если ниже - четырехугольник не существует (во всяком случае полученная фигура выглядит странно)
        if (ZTx == 0) {
            if (ZTy >= Y) {
                double a;
                a = 0.5 * ZTy * X;
                System.out.println("Это треугольник, его площадь равна " + a);
            } else {
                System.out.println("Четырехугольник не существует");
            }
        }
        //Если точка пересечения Z и T лежит на оси X, а координата ZTx правее X, получим треугольник.
        //Если ниже - четырехугольник не существует
        else if (ZTy == 0) {
            if (ZTx >= X) {
                double a;
                a = 0.5 * ZTx * Y;
                System.out.println("Это треугольник, его площадь равна " + a);
            } else {
                System.out.println("Четырехугольник не существует");
            }
        } else {
            //Это все-таки четырехугольник. Проверим не является ли он самопересекающимся
            if (isSelfIntersecting(X, 0, 0, Y, ZTx, ZTy)) {
                //Если четырехугольник является самопересекающимся, его площадь будет состоять  суммы площадей двух треугольников
                System.out.println("Самопересекающийся четырехугольник ");

            /*Определяем через какую сторону идет самопересечение.
            Определяем точку пересечения. Рассчитываем площади треугольников и суммируем.*/
                if (ZTy < 0) {
                    //Координата x точки пересечения
                    double xi;
                    xi = intersectionPointX(0, Y, ZTx, ZTy, 0, 0, X, 0);

                    //Координата y точки пересечения
                    double yi;
                    yi = intersectionPointY(0, Y, ZTx, ZTy, 0, 0, X, 0);

                    //Площадь первого треугольника
                    double a1;
                    a1 = area(new double[]{0, 0, xi}, new double[]{Y, 0, yi});

                    //Площадь второго треугольника
                    double a2;
                    a2 = area(new double[]{xi, X, ZTx}, new double[]{yi, 0, ZTy});

                    //Площадь самопересекающегося четырехугольника
                    double a;
                    a = a1 + a2;
                    System.out.println("Самопересечение идет через сторону X. Площадь четырехугольника равна " + a);
                } else {
                    //Координата x точки пересечения
                    double xi;
                    xi = intersectionPointX(0, 0, 0, Y, ZTx, ZTy, X, 0);

                    //Координата y точки пересечения
                    double yi;
                    yi = intersectionPointY(0, 0, 0, Y, ZTx, ZTy, X, 0);

                    //Площадь первого треугольника
                    double a1;
                    a1 = area(new double[]{0, xi, ZTx}, new double[]{Y, yi, ZTy});

                    //Площадь второго треугольника
                    double a2;
                    a2 = area(new double[]{xi, 0, X}, new double[]{yi, 0, 0});

                    //Площадь самопересекающегося четырехугольника
                    double a;
                    a = a1 + a2;
                    System.out.println("Самопересечение идет через сторону Y. Площадь четырехугольника равна " + a);
                }
            } else {
                //находим площадь обычного четырехугольника (выпуклого или вогнутого)
                double a;
                a = area(new double[]{X, 0.0, 0.0, ZTx}, new double[]{0.0, 0.0, Y, ZTy});
                System.out.println("Обычный четырехугольник. Площадь четырехугольника равна " + a);
            }
        }
    }

    //формула площади Гаусса для многоугольника (применяется для расчёта площади четырехугольника и треугольника)
    private static double area(double[] x, double[] y) {

        //Координаты всех точек многоугольника передаются в качестве массивов. Проверяем соответствие длин массивов
        if (x.length == y.length && x.length > 2) {
            //задаем части выражения
            double A = 0.0;
            double B = 0.0;

            //В первой части суммируем произведение x1*y2+x2*y3 и т.д. Во второй - y1*x2+y2*x3 и т.д.
            for (int i = 0; i < x.length - 1; i++) {
                A += x[i] * y[i + 1];
                B += y[i] * x[i + 1];
                if (i == x.length - 2) {
                    //Если список полдошел к концу добавляем финальные элементы
                    A += x[i + 1] * y[0];
                    B += y[i + 1] * x[0];
                }
            }
            return 0.5 * Math.abs(A - B);
        } else {
            //Если длины массивов не совпали или длина массива меньше 3 возвращаем -1 , как индикатор ошибки.
            System.out.println("Площадь многоугольника не может быть рассчитана");
            return -1;
        }
    }

    //формула площади гаусса для четырехугольника (была заменена на фомрулу выше для универсальности)
   /* private static double areaGauss(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return 0.5 * Math.abs((x1 * y2 + x2 * y3 + x3 * y4 + x4 * y1) - (y1 * x2 + y2 * x3 + y3 * x4 + y4 * x1));
    }*/

    //функция определения координаты x точки пересечения двух сегментов прямых
    //Методика описана на сайте http://paulbourke.net/geometry/pointlineplane/
    private static double intersectionPointX(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double ua;
        ua = calcUa(x1, y1, x2, y2, x3, y3, x4, y4);
        return x1 + ua * (x2 - x1);
    }

    //функция определения координаты y точки пересечения двух сегментов прямых
    //Методика описана на сайте http://paulbourke.net/geometry/pointlineplane/
    private static double intersectionPointY(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double ub;
        ub = calcUb(x1, y1, x2, y2, x3, y3, x4, y4);
        return y1 + ub * (y2 - y1);
    }

    //Коэффициент для определения координаты x точки пересечения двух сегментов прямых
    private static double calcUa(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
    }

    //Коэффициент для определения координаты y точки пересечения двух сегментов прямых
    private static double calcUb(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
    }

}

