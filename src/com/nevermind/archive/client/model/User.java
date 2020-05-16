package com.nevermind.archive.client.model;

public class User {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private boolean isAdmin;

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
