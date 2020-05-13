package com.nevermind.library.model.role;

import com.nevermind.library.model.book.Book;

import java.util.ArrayList;

public class User {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
private ArrayList<Book> takenBooks =new ArrayList<>();

    public User(String firstName, String middleName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
