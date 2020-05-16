package com.nevermind.notepad.model.dao;

import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.model.specs.NoteSpecification;

import java.util.List;

public interface NotepadDAO {

    boolean add(String title,String email,String message);

    List<Note> readAll();

    List<Note> search(NoteSpecification spec,boolean sorted);

void onExit();

}
