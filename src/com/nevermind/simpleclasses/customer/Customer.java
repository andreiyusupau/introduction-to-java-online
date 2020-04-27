package com.nevermind.simpleclasses.customer;

/*8. Создать класс Customer, спецификация которого приведена ниже. Определить конструкторы, set- и get- методы
        и метод toString(). Создать второй класс, агрегирующий массив типа Customer, с подходящими конструкторами
        и методами. Задать критерии выбора данных и вывести эти данные на консоль.
        Класс Customer: id, фамилия, имя, отчество, адрес, номер кредитной карточки, номер банковского счета.
        Найти и вывести:
        a) список покупателей в алфавитном порядке;
        b) список покупателей, у которых номер кредитной карточки находится в заданном интервале*/

public class Customer implements Comparable<Customer>{

    private long id;
    private String familyName; //фамилия
    private String firstName; //имя
    private String middleName; //отчество
    private long creditCardNumber; //номер кредитной карты
    private long accountNumber; //номер счета

    //конструктор
    public Customer(String familyName, String firstName, String middleName, long creditCardNumber, long accountNumber) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.creditCardNumber = creditCardNumber;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Покупатель{" +
                "id:" + id +
                ", Фамилия: " + familyName  +
                ", Имя: " + firstName +
                ", Отчество: " + middleName +
                ", Номер кредитной карты: " + creditCardNumber +
                ", Номер счета: " + accountNumber +
                "}\n";
    }

    //метод для сравнения объектов класса по полю Фамилия
    @Override
    public int compareTo(Customer o){
        return   this.familyName.compareTo(o.familyName);
    }

//метод для определения находится ли номер карты покупателя в заданном интервале
    public boolean cardNumberIsInInterval(long a, long b){
        return this.creditCardNumber >= a && this.creditCardNumber <= b;
    }

    //геттеры и сеттеры
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


}
