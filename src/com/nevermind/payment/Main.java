package com.nevermind.payment;

public class Main {

    public static void main(String[] args) {

        Payment pay = new Payment(); //создаем платеж

        //добавляем товары , которые хотим купить
        pay.addProduct("Гречка","Крупа гречневая, высший сорт, 900 г",1.6);
        pay.addProduct("Сахар","Сахар-песок, 450 г",1.1);
        pay.addProduct("Туалетная бумага","Туалетная бумага трехслойная, 4 рулона",2.5);
        pay.addProduct("Макароны","Фетуччини, высший сорт, 500 г",3.2);

        System.out.println(pay.toString()); //выводим информацию о платеже на печать

        pay.sendPayment(); //отправляем платеж

        System.out.println(pay.toString());//выводим информацию о платеже на печать (статус стал "В процессе")

        pay.onReceiving(true); //предположим, что платеж принят клиентом

        System.out.println(pay.toString());//выводим информацию о платеже на печать (статус стал "Успешно")

    }
}
