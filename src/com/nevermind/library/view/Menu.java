package com.nevermind.library.view;

import com.nevermind.library.controller.BookController;
import com.nevermind.library.controller.UserController;
import com.nevermind.library.model.book.Book;
import com.nevermind.library.util.MenuUtil;

import java.util.ArrayList;

public class Menu {

    BookController bc;
    UserController uc;

    public Menu() {
        bc = new BookController(this);
        uc = new UserController(this);
        enterMenu();
    }

    public void enterMenu() {

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("БИБЛИОТЕКА. ДОБРО ПОЖАЛОВАТЬ!");
            System.out.println("1 - Войти\n2 - Зарегистрироваться\n0 - Выход");

            int n;
            n = MenuUtil.readN("Ваши действия: ", 0, 2); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> loginForm(); //перейти в меню ввода логина и пароля
                case 2 -> registerForm();//перейти в меню регистрации
                case 0 -> work = false; //выход из программы
            }
        }
    }

    public void loginForm() {
        System.out.println("ВХОД");
        String email;
        String password;
        email = MenuUtil.readS("Введите email :");
        password = MenuUtil.readS("Введите пароль: ");
        if (uc.login(email, password)) {
            catalogue(1, bc.getPage(1));
        } else {
            enterMenu();
        }
    }

    public void registerForm() {
        System.out.println("РЕГИСТРАЦИЯ НОВОГО ПОЛЬЗОВАТЕЛЯ");
        String firstName;
        String middleName;
        String lastName;
        String email;
        String password = "";
        String passwordRepeat = "r";

        firstName = MenuUtil.readS("Введите ваше имя :");
        middleName = MenuUtil.readS("Введите ваше отчество :");
        lastName = MenuUtil.readS("Введите вашу фамилию :");
        email = MenuUtil.readS("Введите ваш email :");
        while (!password.equals(passwordRepeat)) {
            password = MenuUtil.readS("Введите пароль: ");
            passwordRepeat = MenuUtil.readS("Повторно введите пароль: ");
        } //TODO:рабочий ввод пароля с сокрытием символов
        if (password != null) {
            uc.register(firstName, middleName, lastName, email, password);
        }

    }


    public void catalogue(int page, ArrayList<Book> books) {
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("КАТАЛОГ КНИГ");
            System.out.println("Страница " + page + " из " + bc.pageCount());
            if (books != null && books.size() > 0) {
                for (Book book : books) {
                    System.out.println(book.toString());
                }
            } else {
                System.out.println("Каталог пуст");
            }

            System.out.println("1 - Предыдущая страница\n2 - Следующая страница\n3 - Поиск книги\n" +
                    "4 - Информация о книге\n5 - Добавление книги\n0 - Выход");

            int n;
            n = MenuUtil.readN("Ваши действия: ", 0, 5); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> bc.previousPage(page);
                case 2 -> bc.nextPage(page);
                case 3 -> searchMenu();
                case 4 -> bc.getBookInfo(MenuUtil.readN("Номер книги из каталога: ", 0, 5)); //считываем выбор пользователя);
                case 5 -> {
                    if (uc.isAdmin()) {
                        addBookMenu();
                    } else {
                        recommendBookMenu();
                    }
                    catalogue(1, bc.getPage(1));
                }
                case 0 -> work = false; //выход из программы
            }
        }
    }

    private void addBookMenu() {
        System.out.println("ДОБАВИТЬ КНИГУ");
        boolean isElectronic;
        isElectronic = MenuUtil.readN("Создать 1 - бумажную или 2 - электронную книгу?", 1, 2) == 2;
        if (isElectronic) {
            if (bc.addBook(MenuUtil.readS("Введите название книги :"),
                    MenuUtil.readS("Введите автора книги :"),
                    MenuUtil.readS("Введите издательство книги :"),
                    MenuUtil.readN("Введите год публикации книги :", 0, 2020),
                    MenuUtil.readS("Введите формат электронной книги :"))) {
                uc.notifyUsers("В библиотеку добавлена новая электронная книга");
            }
        } else {
            if (bc.addBook(MenuUtil.readS("Введите название книги :"),
                    MenuUtil.readS("Введите автора книги :"),
                    MenuUtil.readS("Введите издательство книги :"),
                    MenuUtil.readN("Введите год публикации книги :", 0, 2020),
                    MenuUtil.readN("Введите тип переплета (1- твердый , 2 - мягкий): ", 1, 2) == 1)) {
                uc.notifyUsers("В библиотеку добавлена новая бумажная книга");
            }
        }
    }

    private void recommendBookMenu() {
        System.out.println("РЕКОМЕНДОВАТЬ КНИГУ К ДОБАВЛЕНИЮ");
        boolean isElectronic;
        isElectronic = MenuUtil.readN("Рекомендовать 1 - бумажную или 2 - электронную книгу?", 1, 2) == 2;
        StringBuffer sb = new StringBuffer();
        sb.append("Уважаемые администраторы , предлагаю добавить в библиотеку следующую электронную книгу:");
        sb.append("\n");
        sb.append(MenuUtil.readS("Введите название книги :"));
        sb.append("\n");
        sb.append(MenuUtil.readS("Введите автора книги :"));
        sb.append("\n");
        sb.append(MenuUtil.readS("Введите издательство книги :"));
        sb.append("\n");
        sb.append(MenuUtil.readN("Введите год публикации книги :", 0, 2020));
        sb.append("\n");
        if (isElectronic) {
            sb.append(MenuUtil.readS("Введите формат электронной книги :"));
        } else {
            sb.append((MenuUtil.readN("Введите тип переплета (1- твердый , 2 - мягкий): ", 1, 2) == 1) ? "Твердый переплет" : "Мягкий переплет");
        }
        uc.notifyAdmins(sb.toString());
    }

    private void searchMenu() {
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("ПОИСК КНИГ");
            System.out.println("1 - Искать по названию\n2 - Искать по автору\n0 - Назад");

            int n;
            n = MenuUtil.readN("Ваши действия: ", 0, 2); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> System.out.println(bc.searchBookByName(MenuUtil.readS("Введите название книги: ")));
                case 2 -> System.out.println(bc.searchBookByAuthor(MenuUtil.readS("Введите автора книги: ")));
                case 0 -> work = false; //выход из программы
            }

        }
    }

    public void bookInfo(Book book) {

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("Информация о книге");
            System.out.println(book.toString());
            int n;
            if (uc.isAdmin()) {
                System.out.println("1 - Редактировать информацию о книге\n2 - Удалить книгу\n0 - Выход");
                n = MenuUtil.readN("Ваши действия: ", 0, 2); //считываем выбор пользователя
            } else {
                n = 0;
            }
            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> {
                    if (BookController.isElectronic(book)) {
                        bc.updateBook(book.getId(), MenuUtil.readS("Текущее название книги " + book.getName() + ". Введите новое название книги :"),
                                MenuUtil.readS("Текущий автор книги " + book.getAuthor() + ". Введите нового автора книги :"),
                                MenuUtil.readS("Текущее издательство книги " + book.getPublisher() + ". Введите новое издательство книги :"),
                                MenuUtil.readN("Текущий год публикации книги " + book.getYearOfPublishing() + ". Введите новый год публикации книги :", 0, 2020),
                                MenuUtil.readS("Введите формат электронной книги :"));
                    } else {
                        bc.updateBook(book.getId(), MenuUtil.readS("Текущее название книги " + book.getName() + ". Введите новое название книги :"),
                                MenuUtil.readS("Текущий автор книги " + book.getAuthor() + ". Введите нового автора книги :"),
                                MenuUtil.readS("Текущее издательство книги " + book.getPublisher() + ". Введите новое издательство книги :"),
                                MenuUtil.readN("Текущий год публикации книги " + book.getYearOfPublishing() + ". Введите новый год публикации книги :", 0, 2020),
                                MenuUtil.readN("Введите тип переплета (1- твердый , 2 - мягкий): ", 1, 2) == 1);
                    }
                }
                case 2 -> {
                    if (MenuUtil.readN("Удалить выбранную книгу? 1 - да, 2 - нет.", 1, 2) == 1) {
                        bc.deleteBook(book.getId());
                    }
                }
                case 3 -> work = false;
            }
        }

    }
}
