package com.nevermind.aggregation.text;

import java.util.Arrays;

/*1. Создать объект класса Текст, используя классы Предложение, Слово. Методы: дополнить текст, вывести на
        консоль текст, заголовок текста.*/

public class Text {

    private  String title;//Заголовок текста
    Sentence[] sentences; //предложения текста

    //конструктор
    public Text(String title) {

        this.title = title;

        sentences= new Sentence[0];
    }

    //дополнить текст новым предложением
    public Text complementText(Sentence newSentence) {

        sentences = Arrays.copyOf(sentences, sentences.length + 1); //создаем копию массива предложений с размером увеличенным на 1

        sentences[sentences.length - 1] = newSentence; //добавляю новой предложение

        return this; //возвращаем исходный объект для осуществления последовательного вызова
    }


    //вывести а консоль текст, заголовок текста
    public void printText() {

        System.out.println(title);

        for (Sentence s : sentences) {
            s.print();
        }
    }
}

//предложение
class Sentence {

    Word[] words; //слова предложения

    //конструктор
    public Sentence(Word[] words) {
        this.words = words;
    }

    //печать предложения
    public void print() {
        for (Word w : words) {
            System.out.print(w.getWord() + " ");
        }
        System.out.print(".\n");
    }
}

//слово
class Word {

    String word;//слово

    //конструктор
    public Word(String word) {
        this.word = word;
    }

    //метод для построения слов из строки
    public static Word of(String word){
        return new Word(word);
    }

    public String getWord() {
        return word;
    }
}
