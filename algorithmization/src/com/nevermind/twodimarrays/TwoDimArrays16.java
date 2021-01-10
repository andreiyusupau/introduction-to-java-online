package com.nevermind.twodimarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*16. Магическим квадратом порядка n называется квадратная матрица размера nxn, составленная из чисел 1, 2, 3..n^2
так, что суммы по каждому столбцу, каждой строке и каждой из двух больших диагоналей равны между
собой. Построить такой квадрат.*/

public class TwoDimArrays16 {

    public static void main(String[] args) {

        int n;
        n = readN();

        //задаем матрицу целых чисел
        int[][] a;
        a = twoDimArrays15(n);

        System.out.println("Магический квадрат: ");
        for (int[] x : a) {
            System.out.println(Arrays.toString(x));
        }

        //создаем переменную для хранения магического числа
        int magicConstant;
        magicConstant = checkMagicSquare(a);

        //в зависимости от результата проверки квадрата выводим сообщение о правильности его построения (или неправильности)
        if (magicConstant != -1) {
            System.out.println("Магический квадрат проверен на соответствие сумм строк, столбцов и диагоналей магическому числу: " + magicConstant);
        } else {
            System.out.println("Магический квадрат построен неверно");
        }


    }

    private static int[][] twoDimArrays15(int n) {
        // Математическая часть решений всех конфигураций квадрата взята отсюда https://www.wikihow.com/Solve-a-Magic-Square
        if (n % 2 != 0) {
            return magicSquareOdd(n, 1);
        } else if (n % 4 == 0) {
            return magicSquareDoubleEven(n);
        } else {
            return magicSquareSinglyEven(n);
        }
    }

    //для квадрата с размером кратным 4
    private static int[][] magicSquareDoubleEven(int n) {

        //создаем массив
        int[][] m = new int[n][n];

        //переменная хранящая длину четверти стороны
        int s;
        s = n / 4;

        //переменная , хранящая текущее значение для обхода в прямом направлении
        int currentStraight = 1;

        //переменная , хранящая текущее значение для обхода в обратном направлении
        int currentReverse;
        currentReverse = n * n;

        //обход строк
        for (int i = 0; i < n; i++) {

            //обход столбцов
            for (int j = 0; j < n; j++) {

                /*при выполнении данных условий (число лежит либо в крайних четвертях размером n/4 ,
                 либо в центральном квадрате размером n/2) вносим туда значение из обхода в прямом направлении*/
                if ((i < s && j < s) ||
                        (i < s && j >= 3 * s) ||
                        (i >= s && i < 3 * s && j >= s && j < 3 * s) ||
                        (i >= 3 * s && j < s) ||
                        (i >= 3 * s && j >= 3 * s)) {
                    m[i][j] = currentStraight;
                }
                //в противном случае вносим значение для обхода в обратном
                else {
                    m[i][j] = currentReverse;
                }
                //изменяем значения счётчиков , т.к. одна клетка пройдена
                currentStraight++;
                currentReverse--;
            }
        }
        return m;
    }

    //для квадрата с четным размером не кратным четырем
    private static int[][] magicSquareSinglyEven(int n) {

        //делим квадрат на 4 ранвых квадрата, порядок будет n1
        int n1;
        n1 = n / 2;

        //количество цифр в малых квадратах будет равно size
        int size;
        size = n1 * n1;

        //решаем малые квадраты по принципу нечётного квадрата
        int[][] A = magicSquareOdd(n1, 1);
        int[][] B = magicSquareOdd(n1, 1 + size);
        int[][] C = magicSquareOdd(n1, 1 + 2 * size);
        int[][] D = magicSquareOdd(n1, 1 + 3 * size);
//меняем части малых квадратов
        swapLeft(A, D);
        swapRight(C, B);
        //объединяем их в большой квадрат
        return merge(A, B, C, D);
    }

    //обмен элементов в левой части малых квадратов
    private static void swapLeft(int[][] A, int[][] D) {
        int size;
        size = A.length;

        //Меняем местами левые части матрицы А и B
        for (int i = 0; i < size; i++) {

            //особое условие для центральной строки
            if (i == size / 2) {
                System.out.println("if");
                for (int j = 1; j < size / 2 + 1; j++) {
                    //обмен
                    int temp;
                    temp = A[i][j];
                    A[i][j] = D[i][j];
                    D[i][j] = temp;
                }
            }
            // для остальных строк
            else {
                for (int j = 0; j < size / 2; j++) {
                    //обмен
                    int temp;
                    temp = A[i][j];
                    A[i][j] = D[i][j];
                    D[i][j] = temp;
                }
            }

        }
    }

    //обмен элементов в правой части малых квадратов
    private static void swapRight(int[][] C, int[][] B) {
        int size;
        size = C.length;

        //Меняем местами правые части матрицы C и D
        for (int i = 0; i < size; i++) {
            for (int j = size - (size / 2 - 1); j < size; j++) {
                int temp;
                temp = B[i][j];
                B[i][j] = C[i][j];
                C[i][j] = temp;
            }
        }
    }

    //объединение малых квадратов в большой
    private static int[][] merge(int[][] m1, int[][] m2, int[][] m3, int[][] m4) {

        //переменная для хранения размера большого квадрата
        int size;
        size = 2 * m1.length;

        //массив для хранения квадрата
        int[][] m = new int[size][size];

        //обход строк
        for (int i = 0; i < size; i++) {

            //обход столбцов
            for (int j = 0; j < size; j++) {

                //в зависимости от расположения маленького квадрата внутри большого берем числа из маленьких и вставляем в большой
                //таким образом получаем большой квадрат собранный из маленьких
                if (i < size / 2) {
                    if (j < size / 2) {
                        m[i][j] = m1[i][j];
                    } else {
                        m[i][j] = m3[i][j - size / 2];
                    }
                } else {
                    if (j < size / 2) {
                        m[i][j] = m4[i - size / 2][j];
                    } else {
                        m[i][j] = m2[i - size / 2][j - size / 2];
                    }
                }
            }
        }
        return m;
    }

    //для квадрата с нечетным размером
    private static int[][] magicSquareOdd(int n, int start) {


        //создаем массив
        int[][] m = new int[n][n];
        //текущая строка заполняемого элемента
        int ci = 0;
        //текущий столбец заполняемого элемента
        int cj;
        cj = n / 2;

        //целевая строка
        int tj;

        //целевой столбец
        int ti;

        //Переменная number хранит текущее значение числа, которое будет вписываться в квадрат
        int number = start;

        /*заполняем первую ячейку начальным значением
        (для обычного нечетного квадрата это 1, для малых квадратов четного - различные числа)*/
        m[ci][cj] = number;

        //т.к. первое значение добавлено
        number++;

        //пока еще есть незаполненные ячейки
        while (number < n * n + start) {

            /*пытаемся сдвинуть целевую клетку вправо и вверх от текущей,
            если выходим за пределы, то переносимся на противоположную сторону*/
            ti = (ci == 0) ? (n - 1) : (ci - 1);

            tj = (cj == n - 1) ? 0 : (cj + 1);

            //Если клетка оказывается заполненной, то смещаем целевую клетку вниз от текущей
            if (m[ti][tj] != 0) {
                ti = (ci == n - 1) ? 0 : (ci + 1);
                tj = cj;
            }
            //присваиваем целевой клетке значение
            m[ti][tj] = number;

            //делаем целевую клетку текущей
            ci = ti;
            cj = tj;

            //увеличиваем текущее значение на 1
            number++;
        }
        return m;
    }

    //функция для ввода n с клавиатуры
    private static int readN() {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите порядок магического квадрата: ");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не лежит в указанном диапазоне, выводим ошибку, цикл повторяется
                if (n < 3) {
                    System.err.println("Порядок квадрата должен быть больше 2");
                }

                //Если введённое n соответсвует всем условиям, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return n;
    }

    //функция для проверки магическаого квадрата
    private static int checkMagicSquare(int[][] m) {
        //размерность квадрата присваиваем переменной
        int size;
        size = m.length;

        //рассчитаем магическое число - сумму строк, или столбцов, или диагоналей магического квадрата
        int magicConstant;
        magicConstant = (size * (size * size + 1)) / 2;

        //создаем переменные для подсчёта суммы главное и побочной диагоналей
        int mainDiagSum = 0;
        int auxDiagSum = 0;

        //обходим строки
        for (int i = 0; i < size; i++) {

            //создаем переменную для хранения суммы строки
            int rowSum = 0;

            //обходим столбцы
            for (int j = 0; j < size; j++) {
                //находим сумму строки
                rowSum += m[i][j];

                //если элемент находится на главной диагонали , добавляем его к переменной суммы главной диагонали
                mainDiagSum += (i == j) ? m[i][j] : 0;

                //если элемент находится на побочной диагонали , добавляем его к переменной суммы побочнойдиагонали
                auxDiagSum += (i + j == size - 1) ? m[i][j] : 0;
            }

            //проверяем сумму ряда на соответствие магическому числу, если нет - выводим сообщение об ошибке
            if (rowSum != magicConstant) {
                System.out.println("Квадрат построен неверно");
                return -1;
            }
        }

        //проверяем суммы диагоналей на соответствие магическому числу, если нет - выводим сообщение об ошибке
        if (mainDiagSum != magicConstant || auxDiagSum != magicConstant) {
            System.out.println("Квадрат построен неверно");
            return -1;
        }

        //проверка суммы столбцов
        //обход столбцов
        for (int j = 0; j < size; j++) {

            //переменная для хранения суммы столбцов
            int colSum = 0;

            //обход строк
            for (int i = 0; i < size; i++) {
                //суммируем значения элементов столбца
                colSum += m[i][j];
            }

            //проверяем сумму столбца на соответствие магическому числу, если нет - выводим сообщение об ошибке
            if (colSum != magicConstant) {
                System.out.println("Квадрат построен неверно");
                return -1;
            }
        }
        return magicConstant;
    }
}

