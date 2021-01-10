package com.nevermind.linearprograms;

/*6. Для данной области составить линейную программу, которая печатает true, если точка с координатами (х, у)
        принадлежит закрашенной области, и false — в противном случае:*/

//Здесь видятся более грамотные решения, но раз уж тема - "Линейные программы", то решение будет таким :)

public class LinearPrograms6 {

    public static void main(String[] args) {
        int x = 100;
        int y = 0;
        boolean isInside;
        //Для предотвращение выхода за границы области, описанной в функции linearPrograms6
        try {
            isInside = linearPrograms6(x, y);
        } catch (ArrayIndexOutOfBoundsException e) {
            isInside = false;
        }
        System.out.println("Находится ли точка с координатами (" + x + "," + y + ") внутри закрашенной области? Ответ - " + isInside);
    }

    public static boolean linearPrograms6(int x, int y) throws ArrayIndexOutOfBoundsException {
        //составляем массив , отражающий значение true для точки, входящей в закрашенную область, и false для точки, которая не входит в массив.
        boolean[][] z = {{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, true, true, true, true, true, false, false, false, false, false, false},
                {false, false, false, false, true, true, true, true, true, false, false, false, false, false, false},
                {false, false, false, false, true, true, true, true, true, false, false, false, false, false, false},
                {false, false, false, false, true, true, true, true, true, false, false, false, false, false, false},
                {false, false, true, true, true, true, true, true, true, true, true, false, false, false, false},
                {false, false, true, true, true, true, true, true, true, true, true, false, false, false, false},
                {false, false, true, true, true, true, true, true, true, true, true, false, false, false, false},
                {false, false, true, true, true, true, true, true, true, true, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}};

//формулы 7-y и x+6 используются для корректировки вводимых значений относительно центра координат
        return z[7 - y][x + 6];
    }
}
