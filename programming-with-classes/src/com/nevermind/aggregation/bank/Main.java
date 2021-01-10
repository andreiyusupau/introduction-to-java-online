package com.nevermind.aggregation.bank;

public class Main {

    /*4. Счета. Клиент может иметь несколько счетов в банке. Учитывать возможность блокировки/разблокировки
счета. Реализовать поиск и сортировку счетов. Вычисление общей суммы по счетам. Вычисление суммы по
всем счетам, имеющим положительный и отрицательный балансы отдельно.*/

    public static void main(String[] args) {
        //создаем банк
Bank alphaBank = new Bank();

//добавляем клиента
alphaBank.addClient("Федоренко","Григорий","Юрьевич");

//добаляем ему 3 счета
alphaBank.addAccountToClient("Федоренко","Григорий","Юрьевич");
alphaBank.addAccountToClient("Федоренко","Григорий","Юрьевич");
alphaBank.addAccountToClient("Федоренко","Григорий","Юрьевич");

//добавляем/отнимает денги со счетов
alphaBank.changeBalance(0,250.0);
alphaBank.changeBalance(1,-100.0);
alphaBank.changeBalance(2,150.0);

//выводим на экран информацию о счете
alphaBank.showAccount(0);

//сортируем счета
alphaBank.sortAccounts("Федоренко","Григорий","Юрьевич");

//блокируем счет
alphaBank.blockAccount(0);

//активируем счет
alphaBank.activateAccount(0);

//показать отрицательынй баланс по счетам
alphaBank.showNegativeBalance("Федоренко","Григорий","Юрьевич");

//показать положительный баланс по счетам
alphaBank.showPositiveBalance("Федоренко","Григорий","Юрьевич");

//показать баланс по счетам
alphaBank.showBalance("Федоренко","Григорий","Юрьевич");
    }

}
