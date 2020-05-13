package com.nevermind.library.model.book;

public class PaperBook extends Book {
    private boolean hardCover;


    public PaperBook(String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {
        super(name, author, publisher, yearOfPublishing);
        this.hardCover = hardCover;
    }

    @Override
    public String toString() {
        return "Бумажная книга/"+super.toString()+hardCover;
    }
}
