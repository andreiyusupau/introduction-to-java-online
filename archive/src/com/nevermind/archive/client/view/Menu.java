package com.nevermind.archive.client.view;

import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.client.controller.UserController;
import com.nevermind.archive.common.util.Util;

//меню
public class Menu {

    private UserController uc;
    private RecordController rc;

    public Menu() {
    }
//инициализация
    public void init(UserController uc, RecordController rc) {
        this.uc = uc;
        this.rc = rc;
    }

    //приветственное меню
    public void enterMenu() {

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("БИБЛИОТЕКА. ДОБРО ПОЖАЛОВАТЬ!");
            System.out.println("1 - Войти\n2 - Зарегистрироваться\n0 - Выход");

            int n;
            n = Util.readN("Ваши действия: ", 0, 2); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> loginForm(); //перейти в меню ввода логина и пароля
                case 2 -> registerForm();//перейти в меню регистрации
                case 0 -> work = false; //выход из программы
            }
        }
    }

    //логин
    public void loginForm() {
        System.out.println("ВХОД");
        String email;
        String password;
        email = Util.readS("Введите email :");
        password = Util.readS("Введите пароль: ");
        if (uc.login(email, password)) {
            System.out.println("Вход успешен");
            menu();
        } else {
            System.out.println("Не удалось войти");
            enterMenu();
        }
    }

    //форма регистрации (считываем все поля для регистрации пользователя, пароль с повтором, и передаем в контроллер).
    //сокрытие символов пароля с помощью Console.hideInput() в Intellij Idea не работает,
    // а реализация с дополнительным потоком который скрывает символы выглядит громоздко, поэтмоу сокрытие символов не реализовывалось.
    public void registerForm() {
        System.out.println("РЕГИСТРАЦИЯ НОВОГО ПОЛЬЗОВАТЕЛЯ");
        String email;
        String password = "";
        String passwordRepeat = "r";

        email = Util.readS("Введите ваш email :");
        while (!password.equals(passwordRepeat)) {
            password = Util.readS("Введите пароль: ");
            passwordRepeat = Util.readS("Повторно введите пароль: ");
        }
        if (password != null) {
            uc.register(email, password);
        }

    }

    //главное меню
    public void menu() {
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("Архив");

            System.out.println("1 - Создать дело\n2 - Внести изменение в дело\n3 - Просмотреть дело\n" +
                    "4 - Просмотреть все дела\n0 - Выход");

            int n;
            n = Util.readN("Ваши действия: ", 0, 4); //считываем выбор пользователя

            //переходим в определенную ветку программы или выходим из нее
            switch (n) {
                case 1 -> rc.add(
                        Util.readS("Введите имя: "),
                        Util.readS("Введите отчество: "),
                        Util.readS("Введите фамилию: "),
                        Util.readS("Введите дату рождения: "),
                        Util.readN("Введите год поступления: ",1921,2030),
                        Util.readN("Введите год выпуска: ",1921,2030),
                        Util.readS("Введите характеристику: ")
                );
                case 2 ->rc.update(Util.readN("Введите id: ",0,999999),
                        Util.readS("Введите имя: "),
                        Util.readS("Введите отчество: "),
                        Util.readS("Введите фамилию: "),
                        Util.readS("Введите дату рождения: "),
                        Util.readN("Введите год поступления: ",1921,2030),
                        Util.readN("Введите год выпуска: ",1921,2030),
                        Util.readS("Введите характеристику: ")
                );
                case 3 -> System.out.println(rc.read(Util.readN("Введите id: ",0,999999)).toString());
                case 4 -> System.out.println(rc.readAll().toString());
                case 0 -> work = false; //выход из программы
            }
        }
    }
}
