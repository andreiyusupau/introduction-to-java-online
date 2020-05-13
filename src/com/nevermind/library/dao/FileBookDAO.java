package com.nevermind.library.dao;

import com.nevermind.library.model.book.Book;
import com.nevermind.library.model.book.EBook;
import com.nevermind.library.model.book.PaperBook;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileBookDAO implements BookDAO {
    private final String fileName = "bookList.txt";


    public void create(Book book) {
        try {
            File file;
            file= new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            String bookStr;
            bookStr = book.toString();

            BufferedWriter bw ;
            bw= new BufferedWriter(new FileWriter(file, true));
            if(file.length()>0){
                bw.newLine();
            }
            bw.write(bookStr);

            bw.close();
        } catch (IOException io) {
            System.err.println("Ошибка при записи в файл");
        }
    }

    public Book read(int bookId) throws NullPointerException {
        try {
            File file;
            file= new File(fileName);
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            int counter = 0;
            String currLine;
            while ((currLine = br.readLine()) != null && counter < bookId) {
                br.readLine();
                counter++;
            }
            String[] bookDetails;
            bookDetails = currLine.trim().split("/");
            br.close();
            return initBook(bookDetails);
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return null;
    }

    public List<Book> readAll() {
        try {
            List<Book> books = new ArrayList<>();
            File file = new File(fileName);
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));

            String currLine;
            while ((currLine = br.readLine()) != null) {
                Book book;
                String[] bookDetails;
                bookDetails = currLine.trim().split("/");
                book = initBook(bookDetails);
                books.add(book);
            }
            br.close();
            return books;
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return new ArrayList<>();
    }

    public Book initBook(String[] bookDetails) {

        String type;
        type = bookDetails[0];

        String name;
        name = bookDetails[1];
        String author;
        author = bookDetails[2];
        String publisher;
        publisher = bookDetails[3];
        int yearOfPublishing;
        yearOfPublishing = Integer.parseInt(bookDetails[4]);

        if (type.equals("Бумажная книга")) {
            boolean hardCover;
            hardCover = bookDetails[5].equals("true");
            return new PaperBook(name, author, publisher, yearOfPublishing, hardCover);
        } else {
            String format;
            format = bookDetails[5];
            return new EBook(name, author, publisher, yearOfPublishing, format);
        }
    }

    public List<Book> readPage(int page, int booksPerPage) {
        try {
            ArrayList<Book> pageOfBooks = new ArrayList<>();
            File file = new File(fileName);
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
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
            br.close();
            return (pageOfBooks.size() == 0 && page > 1) ? readPage(page - 1, booksPerPage) : pageOfBooks;
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException ioe) {
            System.err.println("Проблема чтения из файла");
        }

        return null;
    }

    public void update(int bookId, Book book) {
        try {
            File file;
            file = new File(fileName);
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));

            String currLine;
            int counter = 0;
            File tempFile;
            tempFile = new File("temp.txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(tempFile));
            while ((currLine = br.readLine()) != null) {
                if(counter>0){
                    bw.newLine();
                }
                if (counter == bookId) {
                    bw.write(book.toString());
                } else {
                    bw.write(currLine);
                }
                counter++;
            }
bw.close();
            br.close();

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

    public void delete(int bookId) {
        try {
            File file;
            file = new File(fileName);
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));

            String currLine;
            int counter = 0;
            File tempFile;
            tempFile = new File("temp.txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(tempFile));
            while ((currLine = br.readLine()) != null) {
                if (counter != bookId) {
                    if(counter>0&&!(counter==1&&bookId==0)){
                        bw.newLine();
                    }
                    bw.write(currLine);
                }
                counter++;
            }
bw.close();
            br.close();


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
}
