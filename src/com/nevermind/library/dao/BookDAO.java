package com.nevermind.library.dao;

import com.nevermind.library.model.book.Book;


import java.util.List;

public interface BookDAO {
  void create(Book book);
    Book read(int bookId);
   List<Book> readAll();
   List<Book> readPage(int page,int booksPerPage);
    void update(int bookId,Book book);
   void delete(int bookId);
}
