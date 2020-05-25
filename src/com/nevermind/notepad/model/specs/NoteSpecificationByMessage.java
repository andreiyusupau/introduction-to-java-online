package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

//фильтр по тексту сообщения
public class NoteSpecificationByMessage implements NoteSpecification{

    private final String desiredMessage;

    public NoteSpecificationByMessage(String desiredMessage) {
        super();
        this.desiredMessage = desiredMessage;
    }


    @Override
    public boolean specified(Note note) {
        return note.getMessage().toLowerCase().contains(desiredMessage.toLowerCase());
    }
}
