package com.nevermind.library.model.book;

//Бумажная книга
public class PaperBook extends Book {
    private final boolean hardCover;//наличие твердого переплета


    public PaperBook(String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {
        super(name, author, publisher, yearOfPublishing);
        this.hardCover = hardCover;
    }

    //для вывода в файл
    @Override
    public String toString() {
        return super.toString() + "Бумажная книга\n" + hardCover;
    }

    //метод для вывода на печать в консоли
    public String print() {
        return super.print() + "Бумажная книга, " + hardCover;
    }
}
