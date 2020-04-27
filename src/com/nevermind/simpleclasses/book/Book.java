package com.nevermind.simpleclasses.book;

import java.math.BigDecimal;
import java.util.Arrays;

/*9. Создать класс Book, спецификация которого приведена ниже. Определить конструкторы, set- и get- методы и
        метод toString(). Создать второй класс, агрегирующий массив типа Book, с подходящими конструкторами и
        методами. Задать критерии выбора данных и вывести эти данные на консоль.
        Book: id, название, автор(ы), издательство, год издания, количество страниц, цена, тип переплета.
        Найти и вывести:
        a) список книг заданного автора;
        b) список книг, выпущенных заданным издательством;
        c) список книг, выпущенных после заданного года.*/

public class Book {

   private long id;
    private  String title; // название
    private  String [] authors; //список авторов
    private  String publisher; //издательство
    private int publicationDate; //год издания
    private int numberOfPages; //количество страниц
    private  BigDecimal price; //цена (BigDecimal - для точности, т.е. при добавлении функций наподобие "скидка 30% на все книги автора")
    private  boolean hardBinding; //наличие твердого переплета

    public Book(String title, String[] authors, String publisher, int publicationDate, int numberOfPages, double price, boolean hardBinding) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
        this.price =BigDecimal.valueOf(price); //для удобства входные данные в double
        this.hardBinding = hardBinding;
    }

    @Override
    public String toString() {
        return "Книга(" +
                "id=" + id +
                ", Название \"" + title  +"\""+
                ", Авторы " + Arrays.toString(authors) +
                ", Издательство " + publisher  +
                ", Дата публикации " + publicationDate +
                ", Число страниц " + numberOfPages +
                ", Цена " + price +" руб."+
               ", "+ (hardBinding ?"твердый переплет":"мягкий переплет")+
                ")\n";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public boolean isHardBinding() {
        return hardBinding;
    }

    public void setHardBinding(boolean hardBinding) {
        this.hardBinding = hardBinding;
    }
}