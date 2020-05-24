package com.nevermind.library.dao;

import com.nevermind.library.model.book.Book;

import java.util.List;

//интерфейс DAO для работы с хранилищем книг. Необходим для унификации доступа со стороны контроллера независимо от типа базы данных
public interface BookDAO {

    boolean create(Book book);

    Book read(int bookId);

    List<Book> readAll();

    List<Book> readPage(int page, int booksPerPage);

    List<Book> searchByName(String name);

    List<Book> searchByAuthor(String author);

    void update(int bookId, Book book);

    void delete(int bookId);

}
