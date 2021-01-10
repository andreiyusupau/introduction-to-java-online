package com.nevermind.library.model.role;

//пользователь (т.к. администратор отличается от обычного пользователя только правами,
// было принято решение использовать поле isAdmin для определния принажности пользователя к администраторам
// наследование здесь было бы громоздким и ненужным решением)
public class User {

    private final String firstName; //имя
    private final String middleName; //отчество
    private final String lastName; //фамилия
    private final String email;
    private final String hashedPassword; //захешированный пароль
    private boolean isAdmin; //принадлежность к группе "администраторы"

    public User(String firstName, String middleName, String lastName, String email, String hashedPassword, boolean isAdmin) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return firstName + "/" + middleName + "/" + lastName + "/" + email + "/" + hashedPassword + "/" + isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
