package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

public interface NoteSpecification {
        boolean specified(Note note);
}
