package com.nevermind.notepad.model;

import java.time.LocalDate;

public class Note {
    private String title;
    private LocalDate date;
    private String email;
    private String message;

    public Note(String title, LocalDate  date, String email, String message) {
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
        return "Note{" +
                "topic='" + title + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
