package com.nevermind.textfile;

/*Задача 1.
Создать объект класса Текстовый файл, используя классы Файл, Директория. Методы: создать, переименовать,
вывести на консоль содержимое, дополнить, удалить*/

public class File {

    private String name;//название
    private Folder parentFolder; //ссылка на папку, в которой файл находится

    //конструктор
    public File(String name, Folder parentFolder) {
        this.name = name;
        this.parentFolder = parentFolder;
    }

    //переименовать
    public void rename(String name) {
        this.name = name;
    }

    //удалить
    public void delete() {
        if (parentFolder != null) { //проверяем, что родительская папка существует
            parentFolder.deleteFile(name); //вызываем метод удаления данного файла у родительской папки
        }
    }

    //вывести на печать
    public void print() {
        System.out.println("Файл " + getName());
    }

    //геттеры
    public String getName() {
        return name;
    }
}
