package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

public class NoteSpecificationByTitle implements NoteSpecification{

        private final String desiredTitle;

        public NoteSpecificationByTitle(String desiredTitle) {
            super();
            this.desiredTitle = desiredTitle;
        }


    @Override
    public boolean specified(Note note) {
        return note.getTitle().toLowerCase().contains(desiredTitle.toLowerCase());
    }
}
