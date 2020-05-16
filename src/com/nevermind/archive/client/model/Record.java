package com.nevermind.archive.client.model;

import java.time.LocalDate;

public class Record {
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
}
