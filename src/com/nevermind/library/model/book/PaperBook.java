package com.nevermind.library.model.book;

public class PaperBook extends Book {
    private boolean hardCover;


    public PaperBook(String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {
        super(name, author, publisher, yearOfPublishing);
        this.hardCover = hardCover;
    }

    @Override
    public String toString() {
        return super.toString() + "Бумажная книга\n" + hardCover;
    }

    public String print() {
        return super.print() + "Бумажная книга, " + hardCover;
    }
}
