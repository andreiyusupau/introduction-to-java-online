package com.nevermind.notepad.view;

import com.nevermind.notepad.controller.NotepadController;
import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.util.Util;

import java.util.ArrayList;

/*Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками
        в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
        Общие пояснения к практическому заданию.
        • В начале работы приложения данные должны считываться из файла, в конце
        работы – сохраняться в файл.
        • У пользователя должна быть возможность найти запись по любому параметру
        или по группе параметров (группу параметров можно определить
        самостоятельно), получить требуемые записи в отсортированном виде, найти
        записи, текстовое поле которой содержит определенное слово, а также
        добавить новую запись.
        • Особое условие: поиск, сравнение и валидацию вводимой информации
        осуществлять с использованием регулярных выражений.
        • Особое условие: проверку введенной информации на валидность должен
        осуществлять код, непосредственно добавляющий информацию.*/
public class Notepad {

    NotepadController nc;

    public Notepad(NotepadController nc) {
        this.nc = nc;
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

    private void searchMenu(){
        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("Поиск заметок");



            System.out.println("1 - Название содержит\n2 - Дата позднее\n3 - Дата ранее\n4 - email\n" +
                    "5 - Текст содержит\n6 - Искать\n7 - Очистить фильтр\n0 - Выход");

            int n;
            n = Util.readN("Ваши действия: ", 0, 6); //считываем выбор пользователя

            //переходим в определенную ветку программы или выходим из нее
            switch (n) {
                case 1 -> nc.addTitleFilter(Util.readS("Название должно содержать: ") );
                case 2 -> nc.addFromDateFilter(Util.readS("Начиная от даты(дд.мм.гггг): ") );
                case 3 -> nc.addToDateFilter(Util.readS("Заканчивая датой(дд.мм.гггг): ") );
                case 4 -> nc.addEmailFilter(Util.readS("Email: ") );
                case 5 -> nc.addMessageFilter(Util.readS("Сообщение содержит: ") );
                case 6 -> System.out.println(nc.search().toString());
                case 7 -> nc.restoreFilter();
                case 0 -> work = false; //выход из программы
            }
        }
    }

}
