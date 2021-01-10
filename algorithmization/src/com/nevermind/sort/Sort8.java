package com.nevermind.sort;

import com.nevermind.decomposition.Decomposition1;
import java.util.Arrays;

 /*8.Даны дроби p1/q1, p2/q2... pn/qn (p,q - натуральные). Составить программу, которая приводит эти дроби к общему
    знаменателю и упорядочивает их в порядке возрастания.
    */

public class Sort8 {

    public static void main(String[] args) {

        int[] p = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] q = new int[]{2, 3, 4, 5, 8, 3, 2, 5};

        System.out.println("Исходные дроби: ");
        for(int i=0;i<p.length;i++){
            System.out.print(p[i]+"/"+q[i]+", ");
        }

        System.out.print("\n");
        sort8(p, q);

        System.out.println("Дроби после приведения к общему знаменателю и сортировки: ");

        for(int i=0;i<p.length;i++){
            System.out.print(p[i]+"/"+q[i]+", ");
        }
    }


    private static void sort8(int[] p, int[] q) {
        //убеждаемся , что массивы имеют одинаковую длину
        if(p.length==q.length) {

        //общий знаменатель будет равен наименьшему общему кратному знаменателей всех дробей
        int lcm;
        lcm = q[0];

        //обходим все элементы массива знаменателей , и находим общий знаменатель с помощью функции из следующей задачи
        for (int i = 1; i < q.length; i++) {
            lcm = Decomposition1.lcm(lcm, q[i]);
        }
        /*Приводим все элементы массива к общему знаменателю (целочисленное деление здесь не может являться проблемой
            так как наименьшее общее кратное делиться на любой знаменатель без остатка*/

        for (int i = 0; i < q.length; i++) {
            p[i] *= lcm / q[i];
            q[i] = lcm;
        }

        //сортируем массив числителей по возрастанию
        Arrays.sort(p);
    }
    }
}



