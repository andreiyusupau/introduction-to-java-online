package com.nevermind.library.controller;

import com.nevermind.library.dao.FileBookDAO;
import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.view.Menu;

import java.io.Serializable;

public class BookController {
    private FileBookDAO bookDAO;
    private Menu menu;
private final int booksPerPage=5;
    
    public void deleteBook(Book book) {
    }

    public void editBook(Book book, String readS, String readS1, String readS2, int readN, Serializable serializable) {
    }

    public boolean isElectronic(Book book) {
        return book instanceof EBook;
    }

    public void getBooks() {
    }

    public void previousPage() {
    }

    public void nextPage() {
    }

    public void getBookInfo(int bookId) {
        bookDAO.read(bookId);
    }

    //модифицировать
    //просматривать
    //искать
    //брать?

}
