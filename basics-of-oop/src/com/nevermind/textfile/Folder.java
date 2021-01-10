package com.nevermind.textfile;
import java.util.Arrays;

/*Задача 1.
Создать объект класса Текстовый файл, используя классы Файл, Директория. Методы: создать, переименовать,
вывести на консоль содержимое, дополнить, удалить*/

public class Folder {
    private String name;

    private File[] files;
    private Folder[] folders;
    private Folder parentFolder;


    public Folder(String name, Folder parentFolder) {
        this.name = name;
        this.parentFolder = parentFolder;
        files = new File[0];
        folders = new Folder[0];
    }

    //добавить файл
    public void createTextFile(String name, String content) {

        if (checkFileName(name)) { //проверяем, что имя свободно

            files = Arrays.copyOf(files, files.length + 1); //расширяем массив файлов

            files[files.length - 1] = new TextFile(name, this, content); //добавляем новый

        } else {
            System.out.println("Имя файла занято");
        }

    }

    //добавить папку
    public void createFolder(String name) {

        if (checkFolderName(name)) { //проверяем, что имя свободно

            folders = Arrays.copyOf(folders, folders.length + 1);//расширяем массив папок

            folders[folders.length - 1] = new Folder(name, this);//добавляем новую

        } else {

            System.out.println("Имя папки занято");
        }
    }


    //метод для удаления папки
    public void deleteFolder(String name) {

        folders = Arrays.stream(folders) //преобразуем массив в поток
                .filter(f -> !(f.getName().equals(name))) //если название папки совпадает с удаляемым , не возвращаем его
                .toArray(Folder[]::new); //создаем новый массив и присваиваем его значеие переменной files
    }

    //метод для удаления файла
    public void deleteFile(String name) {

        files = Arrays.stream(files) //преобразуем массив в поток
                .filter(f -> !(f.getName().equals(name))) //если название файла совпадает с удаляемым , не возвращаем его
                .toArray(File[]::new); //создаем новый массив и присваиваем его значеие переменной files
    }

    public void rename(String name) {
        this.name = name;
    }

    //проверка, что имя файла свободно
    private boolean checkFileName(String name) {

        //обходим список файлов, если хоть одно имя совпадает возвращаем false, иначе - true
        for (File file : files) {
            if (file.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    //проверка, что имя папки свободно
    private boolean checkFolderName(String name) {

        //обходим список папок, если хоть одно имя совпадает возвращаем false, иначе - true
        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    //обращение к файлу
    public File getFile(String name) throws NullPointerException {
        for (File file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    //обращение к папке
    public Folder getFolder(String name) throws NullPointerException {
        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                return folder;
            }
        }
        return null;
    }

    //геттеры
    public String getName() {
        return name;
    }
}
