package com.nevermind.library.model.book;

//абстрактный класс книга
public abstract class Book {
    private int id;
    private final String name; //название
    private final String author; //автор
    private final String publisher; //издатель
    private final int yearOfPublishing; //год публикации

    //конструктор
    public Book(String name, String author, String publisher, int yearOfPublishing) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublishing = yearOfPublishing;
    }

    //геттеры
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

    //для вывода в файл
    @Override
    public String toString() {
        return "[КНИГА]" + "\n" + id + "\n" + name + "\n" + author + "\n" + publisher + "\n" + yearOfPublishing + "\n";
    }

    //метод для вывода на печать в консоли
    public String print() {
        return id + ", " + name + ", " + author + ", " + publisher + ", " + yearOfPublishing + "г., ";
    }
}
