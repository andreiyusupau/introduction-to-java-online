package com.nevermind.onedimarrays;

/*5. Даны целые числа а1 ,а2 ,..., аn . Вывести на печать только те числа, для которых аi > i.
 */

public class OneDimArrays5 {

    public static void main(String[] args) {
        //Задаем массив целых чисел
        int[] a = new int[]{-1, 0, -3, 4, 5, 6, -7, 8, 9};

        oneDimArrays5(a);
    }

    private static void oneDimArrays5(int[] a) {
        //обход массива выполняем с помощью цикла for
        for (int i = 0; i < a.length; i++) {
            //если условие выполняется выводим на печать результат
            if (a[i] > i)
                System.out.println("Элемент массива a[i]="+a[i]+" большего своего индекса i="+i);
        }
    }
}
