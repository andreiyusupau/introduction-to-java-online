package com.nevermind.chararr;

//4. В строке найти количество чисел.

public class CharArr4 {

    public static void main(String[] args) {

        //строка с числами и другими символами
        String s = "123fa4fsd5gf 6_7,\n8()9";

        System.out.println("Строка: ");
        System.out.println(s);

        int counter;
        counter=charArr4(s);

        System.out.println("Количество чисел в строке равно "+counter);
    }

    private static int charArr4(String s) {

        //переводим строку в массив символов
        char[] c;
        c= s.toCharArray();

        //создаем счетчик
        int counter = 0;

        //создаем переменную для определения идут ли несколько цифр последовательно
        boolean notDigit = true;

        //обходим все символы строки
        for (int i = 0; i < s.length(); i++) {

            /*Если текущий символ является цифрой, а также если предыдущий символ не был цифрой,
            считаем что это начало числа , увеличиваем значение счетчика*/
            if (Character.isDigit(c[i])) {
                if(notDigit){
                    counter++;
                    notDigit = false;
                }
            } else { //если это не цифра, меняем значение переменной notDigit на true
                notDigit = true;
            }
        }
        return counter;
    }
}
