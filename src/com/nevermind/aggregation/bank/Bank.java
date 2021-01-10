package com.nevermind.aggregation.bank;

import java.math.BigDecimal;
import java.util.Arrays;

/*4. Счета. Клиент может иметь несколько счетов в банке. Учитывать возможность блокировки/разблокировки
счета. Реализовать поиск и сортировку счетов. Вычисление общей суммы по счетам. Вычисление суммы по
всем счетам, имеющим положительный и отрицательный балансы отдельно.*/

//архитектура данного приложения строится исходя из концепции, что счета не могут передаваться между клиентами
//т.е. банк владеет клиентами, клиенты - счетами (вместо "банк владеет клиентами и счетами")


//Классы клиент и счет сделаны внутренними и приватными, чтобы воздействовать на них можно было только через банк
//Т.е. при реализации реального приложения в классе банк могут быть добавлены проверки , чтобы исключить случаи изменения баланса счета и т.д.
public class Bank {

    private long currentId = 0; //счетчик id для счетов
    private Client[] clients; //список клиентов

    //коструктор по умолчанию
    public Bank() {
        clients = new Client[0];
    }

    //добавить клиента
    public void addClient(String familyName, String firstName, String middleName) {
        clients = Arrays.copyOf(clients, clients.length + 1); //расширяем массив клиентов
        clients[clients.length - 1] = new Client(familyName, firstName, middleName); //добавляем нового
    }

    //добавить счет клиенту
    public void addAccountToClient(String familyName, String firstName, String middleName) {
        Client client = findClient(familyName, firstName, middleName); //ищем клиента
        if (client != null) {
            client.addAccount(new Account(currentId, client, false, 0.0)); //если нашли, добавляем ему новый счет
            currentId++; //увеличиваем счетчик id
        } else {
            System.out.println("Клиент не найден");
        }

    }

    //найти клиента
    private Client findClient(String familyName, String firstName, String middleName) throws NullPointerException {

        for (Client client : clients) {
            if (client.equalsDesired(familyName, firstName, middleName)) { //если ФИО клиента соответствет искомому возвращаем его
                return client;
            }
        }
        return null; //NullPointerException будет обработано в методах, использующих данный метод
    }

    //найти счет
    private Account findAccount(long id) throws NullPointerException {
        for (Client client : clients) {
            return client.findAccount(id); //для каждого клиента используем поиск по счетам
        }
        return null;//NullPointerException будет обработано в методах, использующих данный метод
    }

    //вывести счет
    public void showAccount(long id) {
        Account acc = findAccount(id); //ищем счет
        if (acc != null) {
            System.out.println(acc.toString()); //если нашли выводим на печать
        } else {
            System.out.println("Cчёт с таким id не найден");
        }
    }

    //отсортировать счета клиента
    public void sortAccounts(String familyName, String firstName, String middleName) {
        Client owner = findClient(familyName, firstName, middleName); //ищем клиента
        if (owner != null) {
            owner.sortAccounts(); //если нашли, используем метод класса Client для сортировки счетов
        } else {
            System.out.println("Клиент не найден");
        }
    }

    //заблокировать счет
    public void blockAccount(long id) {
        Account acc = findAccount(id); //ищем счет
        if (acc != null) {
            acc.setBlocked(true); //если нашли, блокируем
        } else {
            System.out.println("Cчёт с таким id не найден");
        }
    }

    //разблокировать счет
    public void activateAccount(long id) {
        Account acc = findAccount(id);//ищем счет
        if (acc != null) {
            acc.setBlocked(false); //если нашли, разблокируем
        } else {
            System.out.println("Cчёт с таким id не найден");
        }
    }

    //показать баланс по счетам клиента
    public void showBalance(String familyName, String firstName, String middleName) {
        Client owner = findClient(familyName, firstName, middleName);//ищем клиента
        if (owner != null) { //если нашли выводим информацию по счетам
            System.out.println("Общая сумма на счетах клиента " + owner.toString() + " составляет " + owner.getBalance());
        } else {
            System.out.println("Клиент не найден");
        }
    }

    //показать положительный баланс по счетам клиента
    public void showPositiveBalance(String familyName, String firstName, String middleName) {
        Client owner = findClient(familyName, firstName, middleName);//ищем клиента
        if (owner != null) { //если нашли выводим информацию по счетам
            System.out.println("Положительная сумма на счетах клиента " + owner.toString() + " составляет " + owner.getPositiveBalance());
        } else {
            System.out.println("Клиент не найден");
        }
    }

    //показать отрицательный баланс по счетам клиента
    public void showNegativeBalance(String familyName, String firstName, String middleName) {
        Client owner = findClient(familyName, firstName, middleName);//ищем клиента
        if (owner != null) { //если нашли выводим информацию по счетам
            System.out.println("Сумма задолженности на счетах клиента " + owner.toString() + " составляет " + owner.getNegativeBalance());
        } else {
            System.out.println("Клиент не найден");
        }
    }

    //изменить баланс счета на указанную сумму
    public void changeBalance(long id, double sum) {
        Account acc = findAccount(id); //ищем счет
        if (acc != null) {
            acc.changeBalance(sum); //если нашли , меняем его сумму
        } else {
            System.out.println("Cчёт с таким id не найден");
        }
    }

    private class Client {

        private String familyName; //фамилия
        private String firstName; //имя
        private String middleName; //отчество
        private Account[] accounts;//счета

        //конструктор
        public Client(String familyName, String firstName, String middleName) {
            this.familyName = familyName;
            this.firstName = firstName;
            this.middleName = middleName;
            accounts = new Account[0];
        }

        //добавить счёт
        public void addAccount(Account account) {
            accounts = Arrays.copyOf(accounts, accounts.length + 1);
            accounts[accounts.length - 1] = account;
            account.setOwner(this);
        }

        // Вычисление общей суммы по счетам.
        public BigDecimal getBalance() { //если счета не заблокированы возвращаем сумму
            return getPositiveBalance().add(getNegativeBalance());
        }

        //Вычисление суммы по всем счетам, имеющим положительный баланс
        public BigDecimal getPositiveBalance() {
            BigDecimal sum = BigDecimal.ZERO;
            for (Account account : accounts) { //если счета не заблокированы и имеют положительный баланс суммируем
                if (account.getBalance().compareTo(BigDecimal.ZERO) > 0 && !account.isBlocked()) {
                    sum = sum.add(account.getBalance());
                }
            }
            return sum;
        }

        //Вычисление суммы по всем счетам, имеющим отрицательный баланс
        public BigDecimal getNegativeBalance() {
            BigDecimal sum = BigDecimal.ZERO;
            for (Account account : accounts) { //если счета не заблокированы и имеют отрциательный баланс суммируем
                if (account.getBalance().compareTo(BigDecimal.ZERO) < 0 && !account.isBlocked()) {
                    sum = sum.add(account.getBalance());
                }
            }
            return sum;
        }

        //поиск счёта по id
        public Account findAccount(long id) throws NullPointerException {
            for (Account account : accounts) {
                if (id == account.getId()) return account; //если id счета равен искомому возвращаем счет
            }

            return null;
        }

        //сортировка счетов по балансу
        public void sortAccounts() {
            Arrays.sort(accounts);
        }

        public boolean equalsDesired(String familyName, String firstName, String middleName) {
            return this.familyName.equals(familyName) && this.firstName.equals(firstName) && this.middleName.equals(middleName);
        }

        @Override
        public String toString() {
            return familyName + " " + firstName + " " + middleName;
        }
    }

    private class Account implements Comparable<Account> {

        private final long ID;
        private Client owner; //владелец
        private boolean blocked; //статус
        private BigDecimal balance; //баланс. BigDecimal - для точности

        @Override
        public String toString() {
            return "Счет{" +
                    "id: " + ID +
                    ", владелец - " + owner.toString() +
                    ", статус -" + (blocked ? "заблокирован" : "активен") +
                    ", баланс :" + balance +
                    '}';
        }

        //конструктор
        public Account(long id, Client owner, boolean blocked, double balance) {
            this.ID = id;
            this.owner = owner;
            this.blocked = blocked;
            this.balance = BigDecimal.valueOf(balance);
        }

        //метод для изменения баланса
        public void changeBalance(double balance) {
            this.balance = this.balance.add(BigDecimal.valueOf(balance));
        }

        //метод для сортировки счетов
        @Override
        public int compareTo(Account o) {
            return this.balance.compareTo(o.balance);
        }

        //геттеры сеттеры
        public long getId() {
            return ID;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public Client getOwner() {
            return owner;
        }

        public void setOwner(Client owner) {
            this.owner = owner;
        }

    }

}
