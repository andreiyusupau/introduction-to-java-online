package com.nevermind.library.model.book;

//электронная книга
public class EBook extends Book {
    private final String format; //формат электронной книги

    public EBook(String name, String author, String publisher, int yearOfPublishing, String format) {
        super(name, author, publisher, yearOfPublishing);
        this.format = format;
    }

    //для вывода в файл
    @Override
    public String toString() {
        return super.toString() + "Электронная книга\n" + format;
    }

    //метод для вывода на печать в консоли
    public String print() {
        return super.print() + "Электронная книга, " + format;
    }
}
