package com.nevermind.archive.client.model;

import java.time.LocalDate;

public class Record {
    long id;
    String firstName;
    String middleName;
    String lastName;
    LocalDate dateOfBirth;
    Status status;
    int yearOfEntry;
    int yearOfGraduation;
    String description;

    enum Status {
        GRADUATED,
        STUDYING,
        EXPELLED
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public int getYearOfEntry() {
        return yearOfEntry;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }

    public String getDescription() {
        return description;
    }
}
