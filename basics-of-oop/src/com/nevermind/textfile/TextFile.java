package com.nevermind.textfile;
/*Задача 1.
Создать объект класса Текстовый файл, используя классы Файл, Директория. Методы: создать, переименовать,
вывести на консоль содержимое, дополнить, удалить*/

//текстовый файл является наследником класса Файл
public class TextFile extends File {

    private String content;//он имеет содержимое (в принципе все файлы имеют содержимое, но в ключе данной задаче будем считать так)

    //конструктор
    public TextFile(String name, Folder parentFolder, String content) {
        super(name, parentFolder); //вызываем конструктор суперкласса
        this.content = content;
    }

    //дополнить (метод отсутствует в суперклассе, для вызова метода потребуется преобразование объекта)
    public void add(String newContent) {
        content += newContent;
    }

    //вывести на консоль содержимое (замещает метод суперкласса)
    public void print() {
        System.out.println("Содержимое файла " + getName() + " :" + content);
    }

}
