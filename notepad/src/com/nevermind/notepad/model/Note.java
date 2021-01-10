package com.nevermind.notepad.model;

import java.time.LocalDate;

//заметка
public class Note {
    private final String title;//название
    private final LocalDate date; //дата
    private final String email; //email
    private final String message; //текст

    public Note(String title, LocalDate date, String email, String message) {
        this.title = title;
        this.date = date;
        this.email = email;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Заметка{" +
                "Тема '" + title + '\'' +
                ", дата " + date +
                ", email '" + email + '\'' +
                ", текст '" + message + '\'' +
                '}';
    }
}
