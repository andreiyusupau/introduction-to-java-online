package com.nevermind.notepad.dao;

import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.model.specs.NoteSpecification;

import java.util.List;

//интерфейс DAO для работы с записями. Необходим для унификации доступа со стороны контроллера независимо от типа базы данных
public interface NotepadDAO {

    boolean add(String title,String email,String message);

    List<Note> readAll();

    List<Note> search(NoteSpecification spec,boolean sorted);

    void onExit();

}
