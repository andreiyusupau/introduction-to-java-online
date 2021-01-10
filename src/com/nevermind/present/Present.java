package com.nevermind.present;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*Задача 5.
Создать консольное приложение, удовлетворяющее следующим требованиям:
• Корректно спроектируйте и реализуйте предметную область задачи.
• Для создания объектов из иерархии классов продумайте возможность использования порождающих шаблонов
проектирования.
• Реализуйте проверку данных, вводимых пользователем, но не на стороне клиента.
• для проверки корректности переданных данных можно применить регулярные выражения.
• Меню выбора действия пользователем можно не реализовывать, используйте заглушку.
• Особое условие: переопределите, где необходимо, методы toString(), equals() и hashCode().

Вариант B. Подарки. Реализовать приложение, позволяющее создавать подарки (объект, представляющий собой
подарок). Составляющими целого подарка являются сладости и упаковка.*/

//TODO:регулярные выражения (пока не понадобились)

//клиент формирующий подарки
class Client {

    ArrayList<Present> presents = new ArrayList<>();//список подарков

    public Client() {

    }

    //меню
    public void menu() {

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - Создать подарок\n2 - Просмотреть подарки\n3 - Выход");

            int n;
            n = readN("Ваши действия: ", 1, 3); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> createPresentMenu(); //перейти в меню добавления подарка
                case 2 -> showPresents();//перейти в меню получения информации о подарках
                case 3 -> work = false; //выход из программы
            }
        }
    }

    public void createPresentMenu() {

        Present.PresentBuilder pb = new Present.PresentBuilder(); //создаем "строителя" подарков

        //цикл для работы с меню до нажатия кнопки "выход"
        boolean work = true;
        while (work) {
            System.out.println("СОЗДАТЬ ПОДАРОК");
            System.out.println("1 - Указать получетеля\n2 - Изменить размер упаковки\n3 - Изменить цвет упаковки\n" +
                    "4 - Добавить определенное количество конфет\n5 - Добавить случайное количество конфет\n" +
                    "6 - Сформировать стандартный подарок\n7 - Создать");

            int n;
            n = readN("Ваши действия: ", 1, 7); //считываем выбор пользователя

            //переходим в определнную ветку программы или выходим из нее
            switch (n) {
                case 1 -> pb.addRecipient(readS("Введите имя получателя подарка: ")); //считываем ввод имени получателя
                case 2 -> pb.changeWrappingSize(readN("Введите размер упаковки: ")); //изменяем размер коробки
                case 3 -> pb.changeWrappingColor(readS("Введите цвет упаковки (выбирайте из стандартных): "));//изменяем цвет упаковки
                case 4 -> pb.addCandies(readS("Введите название конфет: "), readN("Введите количество конфет: "));//добавить фиксированное количество конфет
                case 5 -> pb.addRandomCountOfCandies(readS("Введите название конфет: "));//добавить слуайное количество конфет
                case 6 -> pb.addRecipient("Безымянный пионер").changeWrappingColor("синий").changeWrappingSize(25).addCandies("Алёнка", 10)
                        .addCandies("Белочка", 10).addCandies("Коровка", 5); //сформировать стандартный подарок
                case 7 -> work = false; //назад
            }
        }

        presents.add(pb.build()); //создать подарок
    }

    //вывести информацию обо всех созданных подарках
    public void showPresents() {
        System.out.println(presents.toString());
    }

    //функция для ввода n с клавиатуры (для работы с меню)
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


    //функция для ввода n с клавиатуры без проверки (по условию задачи требуется проводить проверку не на стороне пользователя)
    private static int readN(String question) {

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
                break;
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

public class Present {
    private String recipient;
    private Wrapping wrapping;
    private Candy[] candies;

    public Present(String recipient, Wrapping wrapping, Candy[] candies) {
        this.recipient = recipient;
        this.wrapping = wrapping;
        this.candies = candies;
    }

    @Override
    public String toString() {
        return "Подарок для " + recipient + "." + wrapping.toString() + "Общее число конфет: " + candies.length + ", в составе: " + Arrays.toString(candies) + "\n";
    }

    //Шаблон строитель
    static class PresentBuilder {

        private String recipient;//адресат
        private Wrapping wrapping;//коробка
        private Candy[] candies; //конфеты

        //конструктор
        PresentBuilder() {
            recipient = "[Здесь должно быть имя получателя]";
            wrapping = new Wrapping();
            candies = new Candy[0];
        }

        //добавить имя получателя
        public PresentBuilder addRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        //изменить цвет (цвет вводится с клавиатуры св текстовом формате)
        public PresentBuilder changeWrappingColor(String inputColor) {
            inputColor = inputColor.toLowerCase(); //для однозначности разбора текста

            //в зависимости от введенного значения присваиваем коробке новый цвет
            Color color;
            color = switch (inputColor) {
                case "зеленый", "зелёный" -> Color.GREEN;
                case "синий" -> Color.BLUE;
                case "красный" -> Color.RED;
                case "оранжевый" -> Color.ORANGE;
                case "голубой" -> Color.CYAN;
                case "фиолетовый" -> Color.MAGENTA;
                case "желтый", "жёлтый" -> Color.YELLOW;
                case "розовый" -> Color.PINK;
                case "черный", "чёрный" -> Color.BLACK;
                default -> Color.WHITE;
            };
            wrapping.setColor(color);
            return this;
        }

        //изменить размер коробки (с проверками не на стороне пользователя)
        public PresentBuilder changeWrappingSize(int size) {
            //если размер больше 0 и больше уже находящихся в коробке конфет, т оприсваиваем новый размер
            if (size < 0) {
                System.out.println("Размер коробки должен быть положительным!");
            } else if (size < candies.length) {
                System.out.println("Размер коробки должен вмещать ранее выбранные конфеты");
            } else {
                wrapping.setSize(size);
            }
            return this;
        }

        //добавление конфет
        public PresentBuilder addCandies(String name, int candyCount) {

            //индекс начала добавления новых конфет
            int startIndex;
            startIndex = candies.length;

            //новый размер массива (но не более размера коробки)
            int extendedSize;
            extendedSize = (wrapping.getSize() - startIndex > candyCount) ? (startIndex + candyCount) : wrapping.getSize();
            if (startIndex < extendedSize) {
                candies = Arrays.copyOf(candies, extendedSize);

                for (int i = startIndex; i < extendedSize; i++) {
                    candies[i] = new Candy(name);
                }
            } else {
                System.out.println("Коробка заполнена!");
            }

            return this;
        }

        //добавить случайное число конфет от 1 до 10
        public PresentBuilder addRandomCountOfCandies(String name) {
            int candyCount;
            candyCount = (int) (Math.random() * 10) + 1;
            return addCandies(name, candyCount);
        }

        //создать подарок с установленными параметрами
        public Present build() {
            return new Present(recipient, wrapping, candies);
        }
    }
}

class Wrapping {
    private Color color; //цвет
    private int size; //размер

    //конструктор со стандартными значениями полей
    public Wrapping() {
        size = 10;
        color = Color.WHITE;
    }

    @Override
    public String toString() {
        return "Коробка цвета " + color.toString() + " на " + size + " конфет.";
    }

    public int getSize() {
        return size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

}

//конфета
class Candy {
    private String name; //название

    public Candy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\"" + name + "\"";
    }
}
