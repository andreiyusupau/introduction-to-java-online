package com.nevermind.chararr;

/*5. Удалить в строке все лишние пробелы, то есть серии подряд идущих пробелов заменить на одиночные пробелы.
            Крайние пробелы в строке удалить.*/

public class CharArr5 {

    public static void main(String[] args) {

        //строка с лишними пробелами
        String s = " one  two     three     ";

        System.out.println("Исходная строка: ");
        System.out.println(s);

        String s1;
        s1 = charArr5(s);

        System.out.println("Строка после удаления лишних пробелов: ");
        System.out.println(s1);
    }

    private static String charArr5(String s) {

        //создаем  массив символов для хранения исходной строки
        char[] c = s.toCharArray();

        //создаем массив для хранения строки после корректировки
        char[] c2 = new char[c.length];

        int j = 0;

        boolean isFirst = true;

        //обходим исходный массив
        for (int i = 0; i < c.length; ) {

            //если элемент не равен пробелу
            if (c[i] != ' ') {

                /*Если это первый символ(не пробел) в строке или в слове,
                или предыдущий символ не является пробелом, вносим символ в новый массив*/
                if (i == 0 || c[i - 1] != ' ' || isFirst) {

                    c2[j] = c[i];
                    j++;
                    isFirst = false;
                }

                //иначе вносим пробел и символ за ним
                else {

                    c2[j] = ' ';
                    c2[j + 1] = c[i];
                    j += 2;
                }
            }
            i++;
        }
        return String.valueOf(c2, 0, j);
    }
}
