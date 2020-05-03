package com.nevermind.payment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

/*Задача 2.
Создать класс Payment с внутренним классом, с помощью объектов которого можно сформировать покупку из
нескольких товаров*/

public class Payment {

    private final UUID id; //уникальный id платежа
    private Product[] purchase; //список товаров
    private BigDecimal amount = BigDecimal.ZERO; //сумма к оплате
    private PaymentType type = PaymentType.CARD; //способ оплаты
    private PaymentStatus status = PaymentStatus.PREPARATION; //статус платежа

    public Payment() {
        this.id = UUID.randomUUID(); //генерируем псевдослучайный id
        purchase = new Product[0]; //создаем пустой список товаров
    }

    //добавить товар
    public void addProduct(String name, String description, double price) {

        Product product = new Product(name, description, price); //создаем новый объект товара

        purchase = Arrays.copyOf(purchase, purchase.length + 1); //расширяем массив покупок

        purchase[purchase.length - 1] = product; //добавляем туда новый товар

        amount = amount.add(product.getPrice()); //увеличиваем сумму платежа на стоимость товара
    }

    //оплатить
    public void sendPayment() {
        if (checkPayment())
            status = PaymentStatus.IN_PROGRESS;
        else
            System.out.println("Неверные данные");
    }

    //при получении платежа он либо является успешным, либо отклоняется
    public void onReceiving(boolean result) {
        status = result ? PaymentStatus.SUCCESS : PaymentStatus.REJECTED;
    }

    /*Проверка платежа перед отправкой.
     В списке должна быть хоть одна покупка, ее стоимость должна быть положительной.
     Платеж должен быть на стадии "подготовка.*/
    private boolean checkPayment() {
        return purchase.length > 0 && amount.compareTo(BigDecimal.ZERO) > 0 && status.equals(PaymentStatus.PREPARATION);
    }

    @Override
    public String toString() {
        return "Платеж {" +
                "id: " + id +
                ", список покупок:\n" + Arrays.toString(purchase) +
                ", сумма: " + amount +" руб."+
                ", способ оплаты: " + type.getName() +
                ", статус платежа: " + status.getName() +
                '}';
    }

    //геттеры и сеттеры
    public UUID getId() {
        return id;
    }

    //товар
    class Product {

        private String name; //название товара
        private String description; //описание товара
        private BigDecimal price; //стоимость товара

        public Product(String name, String description, double price) {
            this.name = name;
            this.description = description;
            this.price = BigDecimal.valueOf(price);
        }

        public BigDecimal getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Товар {" +
                    "Название: \"" + name + '\"' +
                    ", Описание: \"" + description + '\"' +
                    ", Стоимость: " + price + " руб." +
                    "}\n";
        }
    }

    //способ оплаты
    enum PaymentType {

        CASH("Наличные"),
        CARD("Банковская карта"),
        ERIP("ЕРИП");

        private final String name;

        PaymentType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    //статус платежа
    enum PaymentStatus {

        PREPARATION("Подготовка"),
        IN_PROGRESS("В процессе"),
        SUCCESS("Успешно"),
        REJECTED("Отклонено");

        private final String name;

        PaymentStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
