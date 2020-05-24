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

            System.out.println("1 - Создать дело\n2 - Внести изменение в дело\n3 - Просмотреть дело\n" +
                    "4 - Просмотреть все дела\n0 - Выход");

            int n;
            n = ClientUtil.readN("Ваши действия: ", 0, 4); //считываем выбор пользователя

            //переходим в определенную ветку программы или выходим из нее
            switch (n) {
                case 1 -> rc.add(
                        ClientUtil.readS("Введите имя: "),
                        ClientUtil.readS("Введите отчество: "),
                        ClientUtil.readS("Введите фамилию: "),
                        ClientUtil.readS("Введите дату рождения: "),
                        ClientUtil.readN("Введите год поступления: ",1921,2030),
                        ClientUtil.readN("Введите год выпуска: ",1921,2030),
                        ClientUtil.readS("Введите характеристику: ")
                );
                case 2 ->rc.update(ClientUtil.readN("Введите id: ",0,999999),
                        ClientUtil.readS("Введите имя: "),
                        ClientUtil.readS("Введите отчество: "),
                        ClientUtil.readS("Введите фамилию: "),
                        ClientUtil.readS("Введите дату рождения: "),
                        ClientUtil.readN("Введите год поступления: ",1921,2030),
                        ClientUtil.readN("Введите год выпуска: ",1921,2030),
                        ClientUtil.readS("Введите характеристику: ")
                );
                case 3 -> System.out.println(rc.read(ClientUtil.readN("Введите id: ",0,999999)).toString());
                case 4 -> System.out.println(rc.readAll().toString());
                case 0 -> {
                    work = false;
                    //nc.onExit();
                } //выход из программы
            }
        }
    }
}
