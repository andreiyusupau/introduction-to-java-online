package com.nevermind.library.model.book;

public abstract class Book {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[КНИГА]" + "\n" + id + "\n" + name + "\n" + author + "\n" + publisher + "\n" + yearOfPublishing + "\n";
    }

    public String print() {
        return id + ", " + name + ", " + author + ", " + publisher + ", " + yearOfPublishing + "г., ";
    }
}
