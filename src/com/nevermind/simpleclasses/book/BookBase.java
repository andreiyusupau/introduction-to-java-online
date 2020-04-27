package com.nevermind.simpleclasses.book;

import java.util.Arrays;

/*9. Создать класс Book, спецификация которого приведена ниже. Определить конструкторы, set- и get- методы и
        метод toString(). Создать второй класс, агрегирующий массив типа Book, с подходящими конструкторами и
        методами. Задать критерии выбора данных и вывести эти данные на консоль.
        Book: id, название, автор(ы), издательство, год издания, количество страниц, цена, тип переплета.
        Найти и вывести:
        a) список книг заданного автора;
        b) список книг, выпущенных заданным издательством;
        c) список книг, выпущенных после заданного года.*/

public class BookBase {

    private Book[] books; //список книг
    private long currentId = 0;//текущий id для присвоения

    //конструктор  по умолчанию
    public BookBase() {
        books = new Book[0];
    }

    //конструктор с входными параметрами
    public BookBase(Book[] books) {
        this.books = books;
        for (Book b : books) {
            b.setId(currentId);
            currentId++;
        }
    }

    //добавление книги в базу. Копируем текущий массив с добавлением пустого элемента. Вставляем туда новую книгу
    public void addBook(Book book) {
        books = Arrays.copyOf(books, books.length + 1);
        books[books.length - 1] = book;
        book.setId(currentId);
        currentId++;
    }

    //метод для удаления книги
    public void deleteBook(long id) {
        books = Arrays.stream(books) //преобразуем массив в поток
                .filter(b -> !(b.getId() == id)) //если id книги совпадает с удаляемым , не возврращаем его
                .toArray(Book[]::new); //создаем новый массив и присваиваем его значеие переменной books
    }

    //поиск книг автора
    public void findByAuthor(String inputAuthor) {
        System.out.println("Список книг автора " + inputAuthor + " :");
        for (Book b : books) {
            for (String author : b.getAuthors()) {  //если автор книги совпадает с искомым, выводим на печать
                if (author.equals(inputAuthor)) {
                    System.out.println(b.getTitle());
                }
            }
        }
    }
//поиск книг по издателю
    public void findByPublisher(String inputPublisher) {
        System.out.println("Список книг издательства " + inputPublisher + " :");
        for (Book b : books) {
            if (b.getPublisher().equals(inputPublisher)) { //если издатель книги совпадает с искомым, выводим на печать
                System.out.println(b.getTitle());
            }
        }
    }

    //поиск по дате
    public void findByDate(int targetDate) {
        System.out.println("Список книг выпущенных после " + targetDate + " года:");
        for (Book b : books) {
            if (b.getPublicationDate() > targetDate) { //если дата публикации больше требуемой выводим на печать
                System.out.println(b.getTitle());
            }
        }
    }

    //метод для вывода списка книг на печать
    public void print() {
        System.out.println("Список книг:");
        System.out.println(Arrays.toString(books));
    }
}
