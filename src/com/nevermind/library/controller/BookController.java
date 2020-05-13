package com.nevermind.library.controller;

import com.nevermind.library.dao.BookDAO;
import com.nevermind.library.dao.FileBookDAO;
import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.model.book.PaperBook;
import com.nevermind.library.view.Menu;

import java.util.ArrayList;


public class BookController {
    private final BookDAO bookDAO;
    private final Menu menu;
    private final int booksPerPage = 5;

    public BookController(Menu menu) {
        bookDAO = new FileBookDAO();
        this.menu = menu;
    }

    public boolean addBook(String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {

        return bookDAO.create(new PaperBook(name, author, publisher, yearOfPublishing, hardCover));
    }

    public boolean addBook(String name, String author, String publisher, int yearOfPublishing, String format) {
        return bookDAO.create(new EBook(name, author, publisher, yearOfPublishing, format));
    }

    public void recommendBook(String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {

    }

    public void recommendBook(String name, String author, String publisher, int yearOfPublishing, String format) {
    }


    public void deleteBook(int bookId) {
        bookDAO.delete(bookId);
    }

    public void updateBook(int bookId, String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {
        bookDAO.update(bookId, new PaperBook(name, author, publisher, yearOfPublishing, hardCover));
    }

    public void updateBook(int bookId, String name, String author, String publisher, int yearOfPublishing, String format) {
        bookDAO.update(bookId, new EBook(name, author, publisher, yearOfPublishing, format));
    }

    public ArrayList<Book> searchBookByName(String name) {
        return (ArrayList<Book>) bookDAO.searchByName(name);
    }

    public ArrayList<Book> searchBookByAuthor(String author) {
        return (ArrayList<Book>) bookDAO.searchByAuthor(author);
    }

    public static boolean isElectronic(Book book) {
        return book instanceof EBook;
    }

    public ArrayList<Book> getPage(int page) {
        return (ArrayList<Book>) bookDAO.readPage(page, booksPerPage);
    }

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) bookDAO.readAll();
    }

    public void getBookInfo(int bookId) {
        bookDAO.read(bookId);
    }

    public void previousPage(int page) {
        menu.catalogue(page - 1, getPage(page - 1));
    }

    public void nextPage(int page) {
        menu.catalogue(page + 1, getPage(page + 1));
    }


}
