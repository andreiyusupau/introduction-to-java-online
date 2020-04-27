package com.nevermind.simpleclasses.book;

public class Main {

    public static void main(String[] args) {

        //создаем базу книг и наполняем ее
        BookBase bb = new BookBase();
        bb.addBook(new Book("Атлант расправил плечи", new String[]{"Айн Рэнд"}, "Альпина", 2008, 1200, 68.23, true));
        bb.addBook(new Book("Источник", new String[]{"Айн Рэнд"}, "Москва", 2012, 653, 42.13, true));
        bb.addBook(new Book("Цветы для Элджернона", new String[]{"Дэниэл Киз"}, "БелДрук", 2003, 313, 28.02, false));
        bb.addBook(new Book("1984", new String[]{"Джордж Оруэлл"}, "Росмэн", 2016, 422, 38.2, true));
        bb.addBook(new Book("О дивный новый мир", new String[]{"Олдос Хаксли"}, "Росмэн", 2011, 344, 29.99, true));
        bb.addBook(new Book("50 оттенков серого", new String[]{"Эрика Леонард"}, "Grey Publishing", 2015, 297, 45.2, false));
        bb.addBook(new Book("Трудно быть богом", new String[]{"Аркадий Стругацкий","Борис Стругацкий"}, "ЛенинградПечать", 1989, 320, 19.2, true));

        bb.print();//выводим список книг на печать

        bb.findByAuthor("Айн Рэнд"); //выводим книги автора

        bb.findByAuthor("Борис Стругацкий"); //выводим книги автора

        bb.findByDate(2011); //выводим книги изданные после этой даты

        bb.findByPublisher("Росмэн"); //выводим книги этого издательства

        bb.deleteBook(5); //удаляем книгу из списка

        bb.print();//выводим список книг на печать
    }
}
