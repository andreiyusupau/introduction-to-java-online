package com.nevermind.simpleclasses.customer;

import java.util.Arrays;

/*8. Создать класс Customer, спецификация которого приведена ниже. Определить конструкторы, set- и get- методы
        и метод toString(). Создать второй класс, агрегирующий массив типа Customer, с подходящими конструкторами
        и методами. Задать критерии выбора данных и вывести эти данные на консоль.
        Класс Customer: id, фамилия, имя, отчество, адрес, номер кредитной карточки, номер банковского счета.
        Найти и вывести:
        a) список покупателей в алфавитном порядке;
        b) список покупателей, у которых номер кредитной карточки находится в заданном интервале*/

public class CustomerBase {


    private Customer[] customers; //список покупателей
    private long currentId=0; //текущий id

    //конструктор по умолчанию
    public CustomerBase() {
        customers = new Customer[0];
    }

    //конструктор с входными параметрами
    public CustomerBase(Customer[] customers) {
        this.customers = customers;
        for(Customer c:customers){
            c.setId(currentId);
            currentId++;
        }
    }

    //метод для добавления нового покупателя
    //копируем исходный массив с покупателями добавляя в конце один пустой элемент, и вставляем туда нового покупателя
    public void addCustomer(Customer customer) {
        customers = Arrays.copyOf(customers, customers.length + 1);
        customers[customers.length - 1] = customer;
        customer.setId(currentId);
        currentId++;
    }

    //метод для удаления покупателя
    public void deleteCustomer(long id) {
        customers = Arrays.stream(customers) //преобразуем массив в поток
                .filter(c -> !(c.getId() == id)) //если id покупателя совпадает с удаляемым , не возврращаем его
                .toArray(Customer[]::new); //создаем нвоый массив и присваиваем его значеие переменной customers
    }

    //метод для вывода списка покупателей на печать с предварительной сортировкой в алфавитном порядке
    public void print() {
        Arrays.sort(customers);
        System.out.println("Список покупателей в алфавитном порядке:");
        System.out.println(Arrays.toString(customers));
    }

    //метод для вывода списка покупателей, у которых номер карты лежит в заданном диапазоне, на печать
    //проверка на соответствие номера осуществляется методом класса Customer
    public void printIf(long a, long b) {
        System.out.println("Покупатели, у которых номер карты лежит в пределах от " + a + " до " + b);
        for (Customer c : customers) {
            if (c.cardNumberIsInInterval(a, b)) {
                System.out.println(c.toString());
            }
        }
    }
}
