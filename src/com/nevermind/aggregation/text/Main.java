package com.nevermind.aggregation.text;

public class Main {

    public static void main(String[] args) {

        //создаем предложения
        Sentence s1 = new Sentence(new Word[]{Word.of("Lorem"), Word.of("ipsum"), Word.of("dolor"), Word.of("sit"), Word.of("amet")});
        Sentence s2 = new Sentence(new Word[]{Word.of("Consectetur"), Word.of("adipiscing"), Word.of("elit")});
        Sentence s3 = new Sentence(new Word[]{Word.of("Sed"), Word.of("do"), Word.of("eiusmod"), Word.of("tempor"), Word.of("incididunt")});

        //создаем текст
        Text t1 = new Text("My Title");

        //дополняем его созданными ранее предложениями
        t1.complementText(s1)
                .complementText(s2)
                .complementText(s3);

        //выводим текст на печать
        t1.printText();
    }
}
