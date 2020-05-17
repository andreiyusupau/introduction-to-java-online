package com.nevermind.archive.client.view;

import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.client.controller.UserController;
import com.nevermind.archive.client.util.ClientUtil;

public class Menu {

    private UserController uc;
    private RecordController rc;

    public Menu() {
    }

    public void init(UserController uc, RecordController rc) {
        this.uc = uc;
        this.rc = rc;
    }

    public void enterMenu() {

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("БИБЛИОТЕКА. ДОБРО ПОЖАЛОВАТЬ!");
            System.out.println("1 - Войти\n2 - Зарегистрироваться\n0 - Выход");

            int n;
            n = ClientUtil.readN("Ваши действия: ", 0, 2); //считываем выбор пользователя

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
        email = ClientUtil.readS("Введите email :");
        password = ClientUtil.readS("Введите пароль: ");
        if (uc.login(email, password)) {
            System.out.println("Вход успешен");
            menu();
        } else {
            System.out.println("Не удалось войти");
            enterMenu();
        }
    }

    public void registerForm() {
        System.out.println("РЕГИСТРАЦИЯ НОВОГО ПОЛЬЗОВАТЕЛЯ");
        String email;
        String password = "";
        String passwordRepeat = "r";

        email = ClientUtil.readS("Введите ваш email :");
        while (!password.equals(passwordRepeat)) {
            password = ClientUtil.readS("Введите пароль: ");
            passwordRepeat = ClientUtil.readS("Повторно введите пароль: ");
        } //TODO:рабочий ввод пароля с сокрытием символов
        if (password != null) {
            uc.register(email, password);
        }

    }



    public void menu() {
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("Архив");

            System.out.println("1 - Добавить заметку\n2 - Поиск заметок\n0 - Выход");

            int n;
            n = Util.readN("Ваши действия: ", 0, 2); //считываем выбор пользователя

            //переходим в определенную ветку программы или выходим из нее
            switch (n) {
                case 1 -> nc.add(Util.readS("Введите заголовок заметки(должен начинаться с заглавной буквы, не более 20 символов): "),
                        Util.readS("Введите email: "),
                        Util.readS("Введите текст заметки(от 5 до 200 символов, без абзацев)"));
                case 2 -> searchMenu();
                case 0 -> {
                    work = false;
                    nc.onExit();
                } //выход из программы
            }
        }
    }
}
