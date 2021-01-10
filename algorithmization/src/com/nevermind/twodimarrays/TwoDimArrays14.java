package com.nevermind.twodimarrays;

import java.util.Arrays;

    /*14. Сформировать случайную матрицу m x n, состоящую из нулей и единиц, причем в каждом столбце число
    единиц равно номеру столбца.
    */

public class TwoDimArrays14 {

    public static void main(String[] args) {

        //Инициализируем матрицу
        int[][] a;

        a = twoDimArrays14();

        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }
    }


    private static int[][] twoDimArrays14() {
        //генерируем m в пределах от 1 до 10
        int m;
        m = (int) (Math.random() * 9 + 1);

        //генерируем n в пределах от 1 до 10. При этом для выполнения условия задачи n должно быть меньше или равно m
        int n;
        do {
            n = (int) (Math.random() * 9 + 1);
        } while (n > m);
        //создаем массив необходимых размеров
        int[][] a = new int[m][n];

        //инициализируем счётчик единиц в столбце
        int counter;

        //обход столбцов
        for (int j = 0; j < n; j++) {
            //присваиваем счетчику номер столбца
            counter = j;

            //обход строк
            for (int i = 0; i < m; i++) {

                //если все необходимые единицы расставлены, присваиваем ячейке 0
                if (counter == 0) {
                    System.out.println("Обязательный 0");
                    a[i][j] = 0;
                }
                /*если для выполнения условия ( в каждом столбце число единиц равно номеру столбца.) все оставшиеся
                 клетки необходимо заполнить едициами, то присваиваем 1 */
                else if (i + counter == m) {
                    a[i][j] = 1;
                    System.out.println("Обязательная 1");
                    //уменьшаем значение счетчика
                    counter--;
                }
                //В остальных случаях присваиваем случайное число 0/1
                else {
                    System.out.println("Random");
                    a[i][j] = (int) (Math.random() + 0.5);

                    //Если получилось число 1 , уменьшаем значение счетчика
                    if (a[i][j] == 1) {
                        counter--;
                    }
                }
            }
        }
        return a;
    }
}

