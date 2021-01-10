package com.nevermind.textfile;

public class Main {

    public static void main(String[] args) {
//создаем новый каталог верхнего уровня root
        Folder common = new Folder("root", null);

        try { //дальнейшие действия обернуты в try-catch, т.к. возмонжно исключение NullPointerException

            common.createFolder("Work");//внутри него создаем каталог Work

            common.getFolder("Work").createTextFile("My Text", "One two three four!!!");//внутри Work создаем тектовый файл

            common.getFolder("Work").getFile("My Text").print();//выводим текстовый файл на печать

            common.getFolder("Work").getFile("My Text").rename("New Name");//переименовываем текстовый файл

            common.getFolder("Work").getFile("New Name").print();//выводим текстовый файл на печать

            ((TextFile) common.getFolder("Work").getFile("New Name")).add("New text"); //дополняем содержимое текстового файла

            common.getFolder("Work").getFile("New Name").print();//выводим текстовый файл на печать

            common.getFolder("Work").getFile("New Name").delete(); //удаляем текстовый файл

            common.getFolder("Work").getFile("New Name").print();//выводим текстовый файл на печать (ошибка, т.к. он удален)

        } catch (NullPointerException npe) {

            System.err.println("Файл/папка не найден");
        }
    }
}
