package com.nevermind.notepad.model.specs;

import com.nevermind.notepad.model.Note;

//Интерфейс фильтра для поискового запроса (паттерн Specification)
public interface NoteSpecification {
        boolean specified(Note note);
}
