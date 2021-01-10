package com.nevermind.branching;

//4. Заданы размеры А, В прямоугольного отверстия и размеры х, у, z кирпича. Определить, пройдет ли кирпич через отверстие.

public class Branching4 {

    public static void main(String[] args) {
        //размеры отверстия
        int A = 20;
        int B = 90;
        //размеры кирпича
        int x=100;
        int y=20;
        int z=30;

        boolean result;
        result =branching4(A,B,x,y,z);

        System.out.println("Пролезет ли кирпич размером "+x+"x"+y+"x"+z+" в отверстие размером "+A+"x"+B+"? Ответ - "+ result);
    }

    public static boolean branching4(int A, int B, int x, int y, int z) {
        /*Для прохождения кирпича через отверстие требуется чтобы две из его сторон были меньше или равны (со скрипом, но пролезет)
        двух размеров отверстия соответственно.*/
        if (A >= x && (B >= y || B >= z))
            return true;
        else if (A >= y && (B >= x || B >= z))
            return true;
        else if (A >= z && (B >= x || B >= y))
            return true;
        else
            return false;//не пролез
    }
}
