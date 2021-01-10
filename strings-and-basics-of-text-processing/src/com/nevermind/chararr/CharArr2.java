package com.nevermind.chararr;

//2. Замените в строке все вхождения 'word' на 'letter'.

public class CharArr2 {

    public static void main(String[] args) {

        //строка со словами "word"
        String s = "The task of defining what constitutes a \"word\" involves determining where one word ends and another\n" +
                "word begins—in other words, identifying word boundaries. There are several ways to determine where the word\n" +
                "boundaries of spoken language should be placed.";

        System.out.println("Исходная строка:");
        System.out.println(s);

        String s1;
        s1=charArr2(s);

        System.out.println("Строка после замены слов \"word\" на \"letter\": ");
        System.out.println(s1);
    }

   private static String charArr2(String s) {

        //конвертируем строку в массив символов
        char[] c;
        c= s.toCharArray();

        //создаем массив символов увеличенной длины для хранения имзененного текста
        char[] c2 = new char[2 * c.length];

        //задаем переменную для обхода создаваемого массива со словом letter
        int j = 0;

        //обходим исходный массив
        for (int i = 0; i < c.length-3; ) {

            //если находим слово "word", создаем в новом массиве слово letter, смещаем итераторы
            if (c[i] == 'w' && c[i + 1] == 'o' && c[i + 2] == 'r' && c[i + 3] == 'd') {
                c2[j] = 'l';
                c2[j + 1] = 'e';
                c2[j + 2] = 't';
                c2[j + 3] = 't';
                c2[j + 4] = 'e';
                c2[j + 5] = 'r';
                i += 4;
                j += 6;
            } else { //в противном случае смещаем итераторы на 1, записываем текущий символ
                c2[j] = c[i];
                i++;
                j++;
            }

        }

        return String.valueOf(c2, 0, j);
    }
}
