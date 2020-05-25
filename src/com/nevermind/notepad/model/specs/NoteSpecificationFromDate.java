package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

import java.time.LocalDate;

//фильтр по дате (не ранее)
public class NoteSpecificationFromDate implements NoteSpecification{

    private final LocalDate desiredDateFrom;

    public NoteSpecificationFromDate(LocalDate desiredDateFrom) {
        super();
        this.desiredDateFrom = desiredDateFrom;
    }


    @Override
    public boolean specified(Note note) {
        return note.getDate().isAfter(desiredDateFrom);
    }
}

