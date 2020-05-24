package com.nevermind.library.dao;

import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.model.book.PaperBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//реализация DAO для работы с текстовым файлов в качестве базы данных
public class FileBookDAO implements BookDAO {
    private final String fileName = "bookList.txt";//имя файла-хранилища
    private File file = new File(fileName); //сам файл
    private int currentId; //текущий id

    //конструктор
    public FileBookDAO() {

        //считываем все книги и считаем их количество для определения текущего id
        ArrayList<Book> books = (ArrayList<Book>) readAll();
        if (books.size() > 0) {
            currentId = books.get(books.size() - 1).getId() + 1;
        } else {
            currentId = 0;
        }
    }

    //добавить книгу
    @Override
    public boolean create(Book book) {

        //если файл не существует создаем его
        try {
            file.createNewFile();
        } catch (IOException ioe) {
            System.err.println("Не удалось создать новый файл");
            return false;
        }

        //для автоматического закрытия потока ввода будем использовать try-with-resources
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

            book.setId(currentId);//присваиваем книге id

            currentId++; //увеличиваем значение текущего id

            String bookStr;
            bookStr = book.toString();

            //если файл уже содержит записи, переходим на следующую строку
            if (file.length() > 0) {
                bw.newLine();
            }
            bw.write(bookStr); //добавляем запись о книге
            return true;
        } catch (IOException io) {
            System.err.println("Ошибка при записи в файл");
            return false;
        }
    }

    //считать книгу по id
    @Override
    public Book read(int bookId) throws NullPointerException {

        //используется блок try-with-resources для автоматического закрытия потока ввода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            int counter = 0; //счетчик id
            String currLine;
            while ((currLine = br.readLine()) != null && counter < bookId) {
                counter++;
            }
            String[] bookDetails;
            bookDetails = currLine.trim().split("/");
            return initBook(bookDetails);
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return null;
    }

    @Override
    public List<Book> readAll() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            String currLine;
            while ((currLine = br.readLine()) != null) {
                Book book;
                String[] bookDetails;
                bookDetails = currLine.trim().split("/");
                book = initBook(bookDetails);
                books.add(book);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return books;
    }

    public Book initBook(String[] bookDetails) {
        Book book;
        String type;
        type = bookDetails[0];
        int id;
        id = Integer.parseInt(bookDetails[1]);
        String name;
        name = bookDetails[2];
        String author;
        author = bookDetails[3];
        String publisher;
        publisher = bookDetails[4];
        int yearOfPublishing;
        yearOfPublishing = Integer.parseInt(bookDetails[5]);

        if (type.equals("Бумажная книга")) {
            boolean hardCover;
            hardCover = bookDetails[6].equals("true");
            book = new PaperBook(name, author, publisher, yearOfPublishing, hardCover);
        } else {
            String format;
            format = bookDetails[6];
            book = new EBook(name, author, publisher, yearOfPublishing, format);
        }
        book.setId(id);
        return book;
    }

    @Override
    public List<Book> readPage(int page, int booksPerPage) {
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            ArrayList<Book> pageOfBooks = new ArrayList<>();
            int firstBook;
            firstBook = (page - 1) * booksPerPage;
            int lastBook;
            lastBook = firstBook + booksPerPage;
            int counter = 0;
            String currLine;
            while ((currLine = br.readLine()) != null && counter < lastBook) {
                if (counter >= firstBook) {
                    String[] bookDetails;
                    bookDetails = currLine.trim().split("/");
                    pageOfBooks.add(initBook(bookDetails));
                }
                counter++;
            }
            return (pageOfBooks.size() == 0 && page > 1) ? readPage(page - 1, booksPerPage) : pageOfBooks;
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println("Проблема чтения из файла");
        }

        return null;
    }

    @Override
    public void update(int bookId, Book book) {
        File tempFile;
        tempFile = new File("temp.txt");
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file));
             BufferedWriter bw
                     = new BufferedWriter(new FileWriter(tempFile))) {
            String currLine;
            int counter = 0;
            while ((currLine = br.readLine()) != null) {
                if (counter > 0) {
                    bw.newLine();
                }
                if (counter == bookId) {
                    bw.write(book.toString());
                } else {
                    bw.write(currLine);
                }
                counter++;
            }
            if (!file.delete()) {
                System.out.println("Невозможно удалить исходный файл");
            } else if (!tempFile.renameTo(file)) {
                System.out.println("Невозможно переименовать файл");
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println("Проблема чтения из файла");
        }
    }

    @Override
    public void delete(int bookId) {
        File tempFile;
        tempFile = new File("temp.txt");
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file));
             BufferedWriter bw
                     = new BufferedWriter(new FileWriter(tempFile))) {
            String currLine;
            int counter = 0;
            while ((currLine = br.readLine()) != null) {
                if (counter != bookId) {
                    if (counter > 0 && !(counter == 1 && bookId == 0)) {
                        bw.newLine();
                    }
                    bw.write(currLine);
                }
                counter++;
            }
            if (!file.delete()) {
                System.out.println("Невозможно удалить исходный файл");
            } else if (!tempFile.renameTo(file)) {
                System.out.println("Невозможно переименовать файл");
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println("Проблема чтения из файла");
        }
    }

    @Override
    public List<Book> searchByName(String name) {
        String nameLC;
        nameLC = name.toLowerCase();
        List<Book> books = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            String currLine;
            while ((currLine = br.readLine()) != null) {

                String[] bookDetails;
                bookDetails = currLine.trim().split("/");
                if (bookDetails[2].toLowerCase().contains(nameLC)) {
                    Book book;
                    book = initBook(bookDetails);
                    books.add(book);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return books;
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        String authorLC;
        authorLC = author.toLowerCase();
        List<Book> books = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String currLine;
            while ((currLine = br.readLine()) != null) {

                String[] bookDetails;
                bookDetails = currLine.trim().split("/");
                if (bookDetails[3].toLowerCase().contains(authorLC)) {
                    Book book;
                    book = initBook(bookDetails);
                    books.add(book);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return books;
    }
}
