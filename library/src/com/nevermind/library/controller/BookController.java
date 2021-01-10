package com.nevermind.library.controller;

import com.nevermind.library.dao.BookDAO;
import com.nevermind.library.dao.FileBookDAO;
import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.model.book.PaperBook;
import com.nevermind.library.view.Menu;

import java.util.ArrayList;

//контроллер работы с книгами
public class BookController {
    private final BookDAO bookDAO; //ссылка на DAO
    private final Menu menu; //ссылка на view
    private final int booksPerPage = 5; //количество книг на странице при использовании постраничного просмотра

    //конструктор
    public BookController(Menu menu) {
        bookDAO = new FileBookDAO();
        this.menu = menu;
    }

    //добавить бумажную книгу в библиотеку
    public boolean addBook(String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {
        return bookDAO.create(new PaperBook(name, author, publisher, yearOfPublishing, hardCover));
    }

    //добавить электронную книгу в библиотеку
    public boolean addBook(String name, String author, String publisher, int yearOfPublishing, String format) {
        return bookDAO.create(new EBook(name, author, publisher, yearOfPublishing, format));
    }

    //удалить книгу из библиотеки
    public void deleteBook(int bookId) {
        bookDAO.delete(bookId);
    }

    //изменить бумажную книгу
    public void updateBook(int bookId, String name, String author, String publisher, int yearOfPublishing, boolean hardCover) {
        bookDAO.update(bookId, new PaperBook(name, author, publisher, yearOfPublishing, hardCover));
    }

    //изменить электронную книгу
    public void updateBook(int bookId, String name, String author, String publisher, int yearOfPublishing, String format) {
        bookDAO.update(bookId, new EBook(name, author, publisher, yearOfPublishing, format));
    }

    //поиск книги по названию
    public ArrayList<Book> searchBookByName(String name) {
        return (ArrayList<Book>) bookDAO.searchByName(name);
    }

    //поиск книги по автору
    public ArrayList<Book> searchBookByAuthor(String author) {
        return (ArrayList<Book>) bookDAO.searchByAuthor(author);
    }

    //проверка является ли книга электронной
    public static boolean isElectronic(Book book) {
        return book instanceof EBook;
    }

    //запрос определенной страницы списка книг
    public ArrayList<Book> getPage(int page) {
        return (ArrayList<Book>) bookDAO.readPage(page, booksPerPage);
    }

    //запрос всех книг
    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) bookDAO.readAll();
    }

    //запрос информации о книге по id
    public Book getBook(int bookId) {
        return bookDAO.read(bookId);
    }

    //переход на предыдущую страницу
    public void previousPage(int page) {
        if (page > 1) {
            menu.catalogue(page - 1, getPage(page - 1));
        }
    }

    //переход на следующую станицу
    public void nextPage(int page) {
        if (pageCount() > page) {
            menu.catalogue(page + 1, getPage(page + 1));
        }
    }

    //определение количества страниц
    public int pageCount() {
        int books;
        books = bookDAO.readAll().size();
        return books != 0 ? books / booksPerPage + 1 : 0;
    }
}
