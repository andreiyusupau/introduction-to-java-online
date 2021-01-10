package com.nevermind.simpleclasses.customer;


public class Main {

    public static void main(String[] args) {

        //создаем базу покупателей
        CustomerBase cb = new CustomerBase();

        //наполняем ее
        cb.addCustomer(new Customer("Матусевич", "Игорь", "Леонидович", 3014000023423454L, 3014000076858454L));
        cb.addCustomer(new Customer( "Щавелев", "Сергей", "Людвигович", 3014000067563435L, 3014000094850274L));
        cb.addCustomer(new Customer( "Киреева", "Анна", "Викторовна", 3014000023574574L, 3014000093483958L));
        cb.addCustomer(new Customer( "Кудрин", "Нестор", "Петрович", 3014000083750473L, 30140000840327485L));
        cb.addCustomer(new Customer("Селянинова", "Майя", "Дмитриевна", 3014000084037594L, 3014000029473603L));

        //выводим на печать в алфавитном порядке
        cb.print();

        //выводим на печать тех, у кого номер карты лежит в заданном диапазоне
        cb.printIf(3014000020000000L, 3014000030000000L);

        //удаляем покупателя с указанным id
        cb.deleteCustomer(3);

        //выводим на печать в алфавином порядке обновленный список
        cb.print();
    }
}
