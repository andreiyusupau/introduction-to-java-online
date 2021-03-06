package com.nevermind.strobj;

/*10. Строка X состоит из нескольких предложений, каждое из которых кончается точкой, восклицательным или вопросительным
знаком. Определить количество предложений в строке X.*/

public class StrObj10 {

    public static void main(String[] args) {

        //строка
        String X= "One, two, three! Four, five: six. Seven, eight - nine? ";

//счетчик количества предложений в строке
        long count;
        count=strObj10(X);

        System.out.println("Строка:\n\""+X+"\"");
        System.out.println("Количество предложений в строке равно "+ count);

    }

    /*Так как по условию предложения не кончаются многоточием или восклицательным с вопросительным знаком,
    то просто определяем количество вопросительных, восклицательных знаков и точек в строке*/
    private static long strObj10(String X){
        return X.chars() //созадем поток символов
                .filter(c->c=='.'||c=='?'||c=='!') //определяем количество точек, впросительных и восклицательных знаков
                .count(); //считаем
    }
}
