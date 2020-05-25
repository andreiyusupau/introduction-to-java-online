package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

//фильтр по email
public class NoteSpecificationByEmail implements NoteSpecification{

    private final String desiredEmail;

    public NoteSpecificationByEmail(String desiredEmail) {
        super();
        this.desiredEmail = desiredEmail;
    }


    @Override
    public boolean specified(Note note) {
        return note.getEmail().toLowerCase().equals(desiredEmail.toLowerCase());
    }
}
