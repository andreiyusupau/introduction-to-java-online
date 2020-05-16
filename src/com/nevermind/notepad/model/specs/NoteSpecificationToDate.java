package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

import java.time.LocalDate;

public class NoteSpecificationToDate implements NoteSpecification{

    private final LocalDate desiredDateTo;

    public NoteSpecificationToDate(LocalDate desiredDateTo) {
        super();
        this.desiredDateTo = desiredDateTo;
    }


    @Override
    public boolean specified(Note note) {
        return note.getDate().isBefore(desiredDateTo);
    }
}