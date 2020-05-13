package com.nevermind.library.model.book;

public abstract class Book {
    private String name;
    private String author;
    private String publisher;
    private int yearOfPublishing;

    public Book(String name, String author, String publisher, int yearOfPublishing) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    @Override
    public String toString() {
        return name+"/"+author+"/"+publisher+"/"+yearOfPublishing+"/";
    }
}
