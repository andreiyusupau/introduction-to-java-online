package com.nevermind.library.model.book;

public class EBook extends Book {
    private String format;

    public EBook(String name, String author, String publisher, int yearOfPublishing, String format) {
        super(name, author, publisher, yearOfPublishing);
        this.format = format;
    }

    @Override
    public String toString() {
        return super.toString() + "Электронная книга\n" + format;
    }

    public String print() {
        return super.print() + "Электронная книга, " + format;
    }
}
