package com.nevermind.library;

/*Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
Общие требования к заданию:
• Система учитывает книги как в электронном, так и в бумажном варианте.
• Существующие роли: пользователь, администратор.
• Пользователь может просматривать книги в каталоге книг, осуществлять поиск
книг в каталоге.
• Администратор может модифицировать каталог.
• *При добавлении описания книги в каталог оповещение о ней рассылается на
e-mail всем пользователям
• **При просмотре каталога желательно реализовать постраничный просмотр
• ***Пользователь может предложить добавить книгу в библиотеку, переслав её
администратору на e-mail.
• Каталог книг хранится в текстовом файле.
• Данные аутентификации пользователей хранятся в текстовом файле. Пароль
не хранится в открытом виде*/


import com.nevermind.library.dao.FileBookDAO;
import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.model.book.PaperBook;

public class Main {

    public static void main(String[] args) {

        FileBookDAO fbdao=new FileBookDAO();
        Book book=new PaperBook("Атлант","Рэнд","РОСМЭН",1999,true);
        Book book1=new PaperBook("Атлант 2","Рэнд","РОСМЭН",2000,true);
        Book book2=new PaperBook("Атлант 3","Рэнд","РОСМЭН",2000,true);
        Book book3=new EBook("Атлант 4","Рэнд","РОСМЭН",2000,".fb2");
        Book book4=new PaperBook("Атлант 5","Рэнд","РОСМЭН",2000,true);

        fbdao.create(book);
        fbdao.create(book1);
        fbdao.create(book2);
        fbdao.create(book3);
        fbdao.create(book4);
        System.out.println("PAGE");
        System.out.println(fbdao.readPage(2,5));
        System.out.println("READ");
        System.out.println(fbdao.read(0).toString());
        System.out.println("READ ALL");
        System.out.println(fbdao.readAll().toString());
        System.out.println("READ");
        System.out.println(fbdao.read(0).toString());
        System.out.println();
        System.out.println("Delete");
        fbdao.delete(0);
        System.out.println(fbdao.readAll().toString());
       System.out.println("Update");
        fbdao.update(1, new EBook("Test","T","T",1,".fb2"));
        System.out.println(fbdao.readAll().toString());
    }
}
