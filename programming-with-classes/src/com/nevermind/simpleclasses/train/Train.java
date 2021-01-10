package com.nevermind.simpleclasses.train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

/*4. Создайте класс Train, содержащий поля: название пункта назначения, номер поезда, время отправления.
Создайте данные в массив из пяти элементов типа Train, добавьте возможность сортировки элементов массива по
номерам поездов. Добавьте возможность вывода информации о поезде, номер которого введен пользователем.
Добавьте возможность сортировки массив по пункту назначения, причем поезда с одинаковыми пунктами
назначения должны быть упорядочены по времени отправления.*/


public class Train {

//поля класса
    private String destinationPoint; //пункт назначения
    private Integer trainNumber; //номер поезда
    private LocalTime departureTime; //время отправления

//конструктор
    public Train(String destinationPoint, Integer trainNumber, LocalTime departureTime) {
        this.destinationPoint = destinationPoint;
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
    }

    //сортировка массива поездов по номеру
    public static void sortByNumber(Train[] trains) {
        Arrays.sort(trains, Comparator.comparing(Train::getTrainNumber));
    }

    //сортировка массива поездов по пункту назначения, а при равенстве по времени отправления
    public static void sortByDestPoint(Train[] trains) {
        Arrays.sort(trains, Comparator.comparing(Train::getDestinationPoint).thenComparing(Train::getDepartureTime));
    }

//функция поиска информации о поезде
    public static void printTrainInfo(Train[] trains) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print("Введите номер поезда: ");

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не лежит в указанном диапазоне, выводим ошибку, цикл повторяется
                if (n < 1) {
                    System.err.println("Значение n должно быть больше 0");
                }

                //Если введённое n соответсвует всем условиям, ищем поезд
                else {

                    //ищем поезд с указанным номером
                    Train train;
                    train = searchTrainInfo(n, trains);

                    //если поезд существует выводим на печать, если нет выводим, что поезда не сущестует
                    if (train != null) {
                        System.out.println(train.toString());
                    } else {
                        System.out.println("Поезд с таким номером не найден.");
                    }

                    //создаем опрос о повторном поиске
                    String answer = "";

                    //пока ответ не равен да или нет продолжаем опрос
                    while (!answer.equals("да") && !answer.equals("нет")) {
                        System.out.println("Повторить поиск?(да/нет)");
                        answer = br.readLine().toLowerCase(); //переводим в нижний регистр , чтобы "ДА", "Да", "дА" и т.д. тоже работали
                    }
                    //если ответ "нет", прерваем цикл. Иначе идем на новый круг.
                    if (answer.equals("нет")) {
                        break;
                    }
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
    }

    //поиск поезда по номеру
    private static Train searchTrainInfo(int number, Train[] trains) throws NullPointerException {

        //обход массива поездов
        for (Train t : trains) {

            //если номер поезда совпал с искомым возвращаем поезд
            if (t.getTrainNumber() == number) {
                return t;
            }
        }
        //иначе возвращаем null
        return null;
    }

    public String toString() {
        return "\nСтанция отправления: " + destinationPoint + ". Номер поезда: " + trainNumber + ". Время отправления: " + departureTime;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}