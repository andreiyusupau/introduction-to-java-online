package com.nevermind.library.dao;

import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.model.book.PaperBook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

//реализация DAO для работы с текстовым файлов в качестве базы данных
public class FileBookDAO implements BookDAO {
    private final String fileName = "bookList.txt";//имя файла-хранилища
    private File file = new File(fileName); //сам файл

    //конструктор
    public FileBookDAO() {
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
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            int currentId = 0;
            String currLine;
            while ((currLine = br.readLine()) != null) {
                if (currLine.equals("[КНИГА]")) {
                    currentId = Integer.parseInt(br.readLine()) + 1;
                }
            }
            book.setId(currentId);//присваиваем книге id

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

            String currLine;
            int id;
            //ищем строку [КНИГА], за ней считываем id
            while ((currLine = br.readLine()) != null) {
                if (currLine.equals("[КНИГА]")) {

                    id = Integer.parseInt(br.readLine());

                    if (id == bookId) { //если id равен нужному, собираем книгу из параметров
                        return initBook(id, br);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return null;
    }

    //считать из файла все книги
    @Override
    public List<Book> readAll() {
        List<Book> books = new ArrayList<>(); //содаем список для получения книг

        //используется блок try-with-resources для автоматического закрытия потока ввода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String currLine;
            //ищем строку [КНИГА], все что за ней записываем в объект Book, объект добавляем в список
            while ((currLine = br.readLine()) != null) {
                if (currLine.equals("[КНИГА]")) {
                    int id = Integer.parseInt(br.readLine());
                    Book book;
                    book = initBook(id, br);
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

    //вспомогательная функция для инициализации книги
    //(считываем все параметры книги, создаем объект Book из заносим параметры в него)
    private Book initBook(int id, BufferedReader br) throws IOException {
        Book book;
        String name;
        name = br.readLine();
        String author;
        author = br.readLine();
        String publisher;
        publisher = br.readLine();
        int yearOfPublishing;
        yearOfPublishing = Integer.parseInt(br.readLine());
        String type;
        type = br.readLine();

        if (type.equals("Бумажная книга")) {
            boolean hardCover;
            hardCover = br.readLine().equals("true");
            book = new PaperBook(name, author, publisher, yearOfPublishing, hardCover);
        } else {
            String format;
            format = br.readLine();
            book = new EBook(name, author, publisher, yearOfPublishing, format);
        }
        book.setId(id);
        return book;
    }

    //функция четния одной страницы для постраничного просмотра
    @Override
    public List<Book> readPage(int page, int booksPerPage) {

        //используется блок try-with-resources для автоматического закрытия потока ввода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            ArrayList<Book> pageOfBooks = new ArrayList<>();//список для хранения книг

            //первая книга на станице
            int firstBook;
            firstBook = (page - 1) * booksPerPage;

            //последняя книга на странице
            int lastBook;
            lastBook = firstBook + booksPerPage;

            int counter = 0;
            String currLine;

            //заносим в список все книги, порядковый номер которых лежит между firstBook и lastBook
            while ((currLine = br.readLine()) != null && counter < lastBook) {
                if (counter >= firstBook) {
                    if (currLine.equals("[КНИГА]")) {
                        int id;
                        id = Integer.parseInt(br.readLine());
                        Book book;
                        book = initBook(id, br);
                        pageOfBooks.add(book);
                        counter++;
                    }
                } else if (currLine.equals("[КНИГА]")) {
                    counter++;
                }
            }
            //если список пустой, а страница больше первой, пытаемся вывести предыдущую
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

        File tempFile = new File("temp.txt");//создаем временный файл для записи

        //используется блок try-with-resources для автоматического закрытия потока ввода и вывода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file));
             BufferedWriter bw
                     = new BufferedWriter(new FileWriter(tempFile))) {
            String currLine;

            //ищем в списке книгу с нужным id
            while ((currLine = br.readLine()) != null) {

                if (currLine.equals("[КНИГА]")) {
                    int id;
                    id = Integer.parseInt(br.readLine());
                    if (id == bookId) {
                        String bookStr;
                        bookStr = book.toString();

                        //если файл уже содержит записи, переходим на следующую строку
                        if (file.length() > 0) {
                            bw.newLine();
                        }
                        bw.write(bookStr); //добавляем обновленную запись о книге
                    } else { //иначе переносим содержимое файла без изменений
                        if (file.length() > 0) {
                            bw.newLine();
                        }
                        bw.write(currLine);
                        bw.newLine();
                        bw.write(id);
                    }
                } else {//иначе переносим содержимое файла без изменений
                    if (file.length() > 0) {
                        bw.newLine();
                    }
                    bw.write(currLine);
                }

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println("Проблема чтения из файла");
        }

        //заменяем содержимое файла file содержимым файла tempFile. tempFile при этом удаляется.
        try {
            Files.move(Path.of(tempFile.toURI()), Path.of(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Ошибка при замене файла");
        }
    }

    @Override
    public void delete(int bookId) {

        File tempFile = new File("temp.txt");//создаем временный файл для записи

        //используется блок try-with-resources для автоматического закрытия потока ввода и вывода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file));
             BufferedWriter bw
                     = new BufferedWriter(new FileWriter(tempFile))) {
            String currLine;

            //обходим все строки, если нам попадается книга с нужным id, выставляем флаг false,
            //и книга не вносится в новый файл tempFile. остальное содержимое вносится без изменений.
            boolean flag = true;

            while ((currLine = br.readLine()) != null) {

                if (currLine.equals("[КНИГА]")) {
                    flag = true;
                    int id;
                    id = Integer.parseInt(br.readLine());
                    if (id == bookId) {
                        flag = false;
                        br.readLine();
                    } else {
                        if (file.length() > 0) {
                            bw.newLine();
                        }
                        bw.write(currLine);
                        bw.newLine();
                        bw.write(id);
                    }
                } else if (flag) {
                    if (file.length() > 0) {
                        bw.newLine();
                    }
                    bw.write(currLine);
                }

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println("Проблема чтения из файла");
        }

        //заменяем содержимое файла file содержимым файла tempFile. tempFile при этом удаляется.
        try {
            Files.move(Path.of(tempFile.toURI()), Path.of(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Ошибка при замене файла");
        }

    }

    //поиск по названию
    @Override
    public List<Book> searchByName(String name) {
        String nameLC;
        nameLC = name.toLowerCase(); //переводим запрсо в нижний регистр, для более вероятного совпадения

        List<Book> books = new ArrayList<>(); //создаем список для хранения результата запроса

        //используется блок try-with-resources для автоматического закрытия потока ввода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            //обходим весь файл, если запрос содержится в названии книги, добавляем ее в список
            String currLine;
            while ((currLine = br.readLine()) != null) {
                if (currLine.equals("[КНИГА]")) {
                    int id;
                    id = Integer.parseInt(br.readLine());
                    String bookName;
                    bookName = br.readLine();
                    if (bookName.toLowerCase().contains(nameLC)) {
                        String author;
                        author = br.readLine();
                        String publisher;
                        publisher = br.readLine();
                        int yearOfPublishing;
                        yearOfPublishing = Integer.parseInt(br.readLine());
                        String type;
                        type = br.readLine();
                        Book book;
                        if (type.equals("Бумажная книга")) {
                            boolean hardCover;
                            hardCover = br.readLine().equals("true");
                            book = new PaperBook(bookName, author, publisher, yearOfPublishing, hardCover);
                        } else {
                            String format;
                            format = br.readLine();
                            book = new EBook(bookName, author, publisher, yearOfPublishing, format);
                        }
                        book.setId(id);
                        books.add(book);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return books;
    }

    //поиск по автору
    @Override
    public List<Book> searchByAuthor(String author) {
        String authorLC;
        authorLC = author.toLowerCase(); //переводим имя автора в нижний регистр для увеличения вероятности совпадения

        List<Book> books = new ArrayList<>();//создаем список для хранения результата запроса


        //используется блок try-with-resources для автоматического закрытия потока ввода
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String currLine;

            //обходим весь файл, если запрос содержится в имени автора книги, добавляем книгу в список
            while ((currLine = br.readLine()) != null) {
                if (currLine.equals("[КНИГА]")) {
                    int id;
                    id = Integer.parseInt(br.readLine());
                    String bookName;
                    bookName = br.readLine();
                    String authorName;
                    authorName = br.readLine();
                    if (authorName.toLowerCase().contains(authorLC)) {
                        String publisher;
                        publisher = br.readLine();
                        int yearOfPublishing;
                        yearOfPublishing = Integer.parseInt(br.readLine());
                        String type;
                        type = br.readLine();
                        Book book;
                        if (type.equals("Бумажная книга")) {
                            boolean hardCover;
                            hardCover = br.readLine().equals("true");
                            book = new PaperBook(bookName, authorName, publisher, yearOfPublishing, hardCover);
                        } else {
                            String format;
                            format = br.readLine();
                            book = new EBook(bookName, authorName, publisher, yearOfPublishing, format);
                        }
                        book.setId(id);
                        books.add(book);
                    }
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
