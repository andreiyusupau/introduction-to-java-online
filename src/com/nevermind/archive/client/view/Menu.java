package com.nevermind.archive.client.view;

import com.nevermind.archive.client.util.ClientUtil;
import jdk.jshell.execution.Util;

public class Menu {

    public void loginForm() {
        System.out.println("ВХОД");
        String email;
        String password;
        email = ClientUtil.readS("Введите email :");
        password = ClientUtil.readS("Введите пароль: ");
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

        firstName = ClientUtil.readS("Введите ваше имя :");
        middleName = ClientUtil.readS("Введите ваше отчество :");
        lastName = ClientUtil.readS("Введите вашу фамилию :");
        email = ClientUtil.readS("Введите ваш email :");
        while (!password.equals(passwordRepeat)) {
            password = ClientUtil.readS("Введите пароль: ");
            passwordRepeat = ClientUtil.readS("Повторно введите пароль: ");
        } //TODO:рабочий ввод пароля с сокрытием символов
        if (password != null) {
            uc.register(firstName, middleName, lastName, email, password);
        }

    }



    public void menu() {
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("Блокнот");
            for (Note note: nc.readAll()){
                System.out.println(note.toString());
            }
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
