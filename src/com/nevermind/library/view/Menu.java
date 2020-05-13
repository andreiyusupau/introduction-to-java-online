package com.nevermind.library.view;

import com.nevermind.library.controller.BookController;
import com.nevermind.library.controller.UserController;
import com.nevermind.library.model.book.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    BookController bc;
    UserController uc;

    public void enterMenu(){

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("БИБЛИОТЕКА. ДОБРО ПОЖАЛОВАТЬ!");
            System.out.println("1 - Войти\n2 - Зарегистрироваться\n0 - Выход");

            int n;
            n = readN("Ваши действия: ", 0, 2); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> loginForm(); //перейти в меню ввода логина и пароля
                case 2 -> registerForm();//перейти в меню регистрации
                case 0 -> work = false; //выход из программы
            }
        }
    }
    public void loginForm(){
        String email;
        String password;
        email=readS("Введите email :");
        password=readS("Введите пароль: ");
        uc.login(email,password);
    }

    public void registerForm() {
        String firstName;
        String middleName;
        String lastName;
        String email;
        String password;

        firstName=readS("Введите ваше имя :");
        middleName=readS("Введите ваше отчество :");
        lastName=readS("Введите вашу фамилию :");
        email=readS("Введите ваш email :");
        password=readS("Введите пароль: ");
        uc.register(firstName,middleName,lastName,email,password);
    }


    public void catalogue(int from,int to,int page){
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("КАТАЛОГ КНИГ");
            System.out.println("Страница "+page);
          //  bc.getBooks(from,to);
            System.out.println("1 - Предыдущая страница\n2 - Следующая страница\n3 - Поиск книги\n" +
                    "4 - Информация о книге\n5 - Добавление книги\n0 - Выход");

            int n;
            n = readN("Ваши действия: ", 0, 5); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> bc.previousPage();
                case 2 -> bc.nextPage();
                case 3 -> searchMenu();
                case 4 -> bc.getBookInfo(readN("Номер книги из каталога: ", 0, 5)); //считываем выбор пользователя);
                case 5 -> addBookMenu();
                case 0 -> work = false; //выход из программы
            }
        }
    }

    private void addBookMenu() {
    }

    private void searchMenu() {
    }

    public void bookInfo(Book book){
        boolean adminMenu;
        adminMenu= uc.isAdmin();
        boolean isElectronic;
        isElectronic=bc.isElectronic(book);
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("Информация о книге");
            System.out.println(book.toString());
            int n;
            if(adminMenu){
                System.out.println("1 - Редактировать информацию о книге\n2 - Удалить книгу\n0 - Выход");
                n = readN("Ваши действия: ", 0, 2); //считываем выбор пользователя
            }else{
                n = 0;
            }
            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> bc.editBook(book,readS("Текущее название книги"+book.getName()+"Введите новое название книги :"),
                        readS("Текущий автор книги"+book.getAuthor()+"Введите нового автора книги :"),
                        readS("Текущее издательство книги"+book.getPublisher()+"Введите новое издательство книги :"),
                        readN("Текущий год публикации книги"+book.getYearOfPublishing()+"Введите новый год публикации книги :",0,2020),
                        isElectronic?readS("Введите формат электронной книги :"):readN("Тип переплета? 1 - твердый, 2 - мягкий",1,2));
                case 2 -> {
                    if(readN("Удалить выбранную книгу? 1 - да, 2 - нет.",1,2)==1){
                        bc.deleteBook(book);
                }
                }
                case 3 -> work = false;
            }
        }

    }



    //функция для ввода n с клавиатуры
    private static int readN(String question, int min, int max) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print(question);

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не является положительным числом, выводим ошибку, цикл повторяется
                if (n < min || n > max) {
                    System.err.println("Значение должно лежать в пределах от " + min + " до " + max);
                }
                //Если введённое n является подходящим, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return n;
    }

    //функция для ввода s с клавиатуры
    private static String readS(String question) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное значение
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод readLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print(question);

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной s
                s = String.valueOf(br.readLine());

                //Если введённое s пустое повторяем цикл
                if (s.equals("")) {
                    System.err.println("Введите хоть что-нибудь.");
                }
                //Если введённое s является подходящим, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");

            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return s;

    }
}
