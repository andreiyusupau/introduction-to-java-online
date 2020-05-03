package com.nevermind.dragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*Создать консольное приложение, удовлетворяющее следующим требованиям:
• Приложение должно быть объектно-, а не процедурно-ориентированным.
• Каждый класс должен иметь отражающее смысл название и информативный состав.
• Наследование должно применяться только тогда, когда это имеет смысл.
• При кодировании должны быть использованы соглашения об оформлении кода java code convention.
• Классы должны быть грамотно разложены по пакетам.
• Консольное меню должно быть минимальным.
• Для хранения данных можно использовать файлы.
Дракон и его сокровища. Создать программу, позволяющую обрабатывать сведения о 100 сокровищах в пещере
дракона. Реализовать возможность просмотра сокровищ, выбора самого дорогого по стоимости сокровища и
выбора сокровищ на заданную сумму.*/

//TODO:запись/чтение в/из файла

//пещера с сокровищами
public class Cave {

    //список сокровищ (здесь я решил начать применять классы для реализации списков)
    private ArrayList<Treasure> treasures = new ArrayList<>();

    //конструктор
    public Cave() {
    }

    //главное меню
    public void menu() {

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - Добавить сокровища\n2 - Просмотреть сокровища\n3 - Выход");

            int n;
            n = readN("Ваши действия: ", 1, 3); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> addTreasureMenu(); //перейти в меню добавления сокровищ
                case 2 -> showTreasureMenu();//перейти в меню получения информации о сокровищах
                case 3 -> work = false; //выход из программы
            }
        }
    }

    //меню добавления сокровищ
    private void addTreasureMenu() {

        //цикл для работы с меню до нажатия кнопки "назад"
        boolean work = true;
        while (work) {
            System.out.println("ДОБАВЛЕНИЕ СОКРОВИЩ");
            System.out.println("1 - Ручной ввод\n2 - Создать случайное сокровище\n3 - Заполнить случайными сокровищами\n4 - Назад");

            int n;
            n = readN("Ваши действия: ", 1, 4); //считываем выбор пользователя

            switch (n) {
                case 1 -> addTreasure(readS("Введите название добавляемого сокровища: "), //добавляем сокровище по введенным данным
                        readS("Введите описание добавляемого сокровища: "),
                        readN("Введите стоимость добавляемого сокровища: ", 0, Integer.MAX_VALUE));
                case 2 -> generateTreasure(); //создаем случайное сокровище
                case 3 -> generateTreasures(); //заполняем оставшиеся места случайными сокровищами
                case 4 -> work = false; //назад
            }
        }
    }

    //добавить скоровища. Если в пещере хватает места, добавляем сокровище, иначе выводим сообщение о недостатке места.
    private void addTreasure(String name, String description, int value) {
        if (treasures.size() <= 100) {
            treasures.add(new Treasure(name, description, value));
        } else {
            System.out.println("Пещера с сокровищами переполнена. Удалите ненужные сокровища перед добавлением новых.");
        }
    }

    //заполняем пещеру случайными сокровищами. Определяем необхоимое количество сокровищ и генерируем их.
    private void generateTreasures() {
        int treasuresToAdd;
        treasuresToAdd = 100 - treasures.size();
        for (int i = 0; i < treasuresToAdd; i++) {
            generateTreasure();
        }
    }

    //создать сулчайное сокровище
    private void generateTreasure() {

        //название сокровища будет состоять из трех частей [Материал]-[Предмет]-[Свойство]

        String name1;
        String name2;
        String name3;

        //генерируем их случайным образом из нескольких вариантов
        int n1;
        n1 = (int) (Math.random() * 10);
        int n2;
        n2 = (int) (Math.random() * 10);
        int n3;
        n3 = (int) (Math.random() * 10);
        name1 = switch (n1) {
            case 0 -> "Золотой";
            case 1 -> "Серебряный";
            case 2 -> "Нефритовый";
            case 3 -> "Мифриловый";
            case 4 -> "Обсидиановый";
            case 5 -> "Двемеритовый";
            case 6 -> "Даэдрический";
            case 7 -> "Адамантиевый";
            case 8 -> "Орихалковый";
            default -> "Метеоритный";
        };

        name2 = switch (n2) {
            case 0 -> "Клинок";
            case 1 -> "Скипетр";
            case 2 -> "Кистень";
            case 3 -> "Посох";
            case 4 -> "Лук";
            case 5 -> "Доспех";
            case 6 -> "Кинжал";
            case 7 -> "Топор";
            case 8 -> "Щит";
            default -> "Шлем";
        };

        name3 = switch (n3) {
            case 0 -> "Бури";
            case 1 -> "Имплозии";
            case 2 -> "Разрушения";
            case 3 -> "Могущества";
            case 4 -> "Жизни";
            case 5 -> "Адского пламени";
            case 6 -> "Молнии";
            case 7 -> "Тьмы";
            case 8 -> "Света";
            default -> "Некромантии";
        };

        String name;
        name = name1 + " " + name2 + " " + name3;

        //описание для всех будет одно
        String description = "Неизвестный артефакт";

        //генерируем стоимость
        int value;
        value = (int) (Math.random() * 1000000);

        //добавляем сокровище с полученными параметрами
        addTreasure(name, description, value);
    }

    //меню просмотра сокровищ
    private void showTreasureMenu() {

        //цикл для работы с меню до нажатия кнопки "назад"
        boolean work = true;
        while (work) {
            System.out.println("1 - Показать информацию о сокровище\n2 - Показать все сокровища\n3 - Удалить сокровище"
                    + "\n4 - Показать самое ценное сокровище\n5 - Выбрать сокровища на указанную сумму\n6 - Назад");
            int n;
            n = readN("Ваши действия: ", 1, 6); //считываем выбор пользователя

            switch (n) {
                //показать сокровище с введенным индексом
                case 1 -> showTreasure(readN("Введите индекс сокровища,которое нужно показать: ", 0, treasures.size() - 1));

                case 2 -> showAllTreasures();//показать все сокровища

                //удалить сокровище с указанным индексом
                case 3 -> deleteTreasure(readN("Введите индекс сокровища,которое нужно удалить: ", 0, treasures.size() - 1));

                case 4 -> showMostValuableTreasure();//показать самое дорогое сокровище

                //выбрать сокровища на указанную сумму
                case 5 -> chooseTreasuresOnSpecifiedValue(readN("Введите сумму, на которую вы хотите взять сокровищ :", 0, Integer.MAX_VALUE));

                case 6 -> work = false;//назад
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

    //удалить сокровище
    private void deleteTreasure(int index) {
        treasures.remove(index);
    }

    //вывести информацию о сокровище
    private void showTreasure(int index) {
        System.out.println(index + ") " + treasures.get(index).toString());
    }

    //вывести информацию о всех сокровищах
    private void showAllTreasures() {
        for (int i = 0; i < treasures.size(); i++) {
            System.out.println(i + ") " + treasures.get(i).toString());
        }
    }

    //показать самое ценное сокровище
    private void showMostValuableTreasure() {
        int index = 0; //индекс самого ценного сокровища
        int max = 0; //максимальная стоимость

        for (int i = 0; i < treasures.size(); i++) {
            int value;
            value = treasures.get(i).getValue();
            if (value > max) {
                index = i;
                max = value;
            }
        }
        showTreasure(index);
    }

    //выбрать сокровища на указанную сумму
    //(реализован простой алгоритм, обходящий все сокровища и берущий подряд все, на что хватает денег, и показывающий остаток)
    private void chooseTreasuresOnSpecifiedValue(int targetValue) {
        for (int i = 0; i < treasures.size(); i++) {
            int value;
            value = treasures.get(i).getValue();
            if (value < targetValue) {
                showTreasure(i);
                targetValue -= value;
            }
        }
        System.out.println("Остаток: " + targetValue);
    }

    class Treasure {

        private String name; //название сокровища
        private String description; //описание сокровища
        private int value; //стоимость сокровища

        public Treasure(String name, String description, int value) {
            this.name = name;
            this.description = description;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + name + "] - " + value + " талеров - " + description;
        }

        //геттеры
        public int getValue() {
            return value;
        }
    }
}
