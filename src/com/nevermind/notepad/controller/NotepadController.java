package com.nevermind.notepad.controller;

import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.dao.FileNotepadDAO;
import com.nevermind.notepad.model.specs.*;
import com.nevermind.notepad.dao.NotepadDAO;
import com.nevermind.notepad.view.Notepad;

import java.time.LocalDate;
import java.util.ArrayList;

public class NotepadController {

    NoteSpecificationConjunction specs=new NoteSpecificationConjunction();
    NotepadDAO notepadDAO;
    Notepad notepad;
    boolean sorted=false;

    public NotepadController() {
notepadDAO=new FileNotepadDAO();
notepad=new Notepad(this);
notepad.menu();
    }

    public boolean add(String title, String email, String message){
return notepadDAO.add(title, email, message);
    }

    public ArrayList<Note> readAll(){
        return (ArrayList<Note>) notepadDAO.readAll();
    }

    public ArrayList<Note> search(){
return (ArrayList<Note>)notepadDAO.search(specs,sorted);
    }

    public void addTitleFilter(String desiredTitle){
        specs.add(new NoteSpecificationByTitle(desiredTitle));
    }

    public void addFromDateFilter(String desiredDate){
        LocalDate date;
        date=LocalDate.parse(desiredDate);
        specs.add(new NoteSpecificationFromDate(date));
    }

    public void addToDateFilter(String desiredDate){
        LocalDate date;
        date=LocalDate.parse(desiredDate);
        specs.add(new NoteSpecificationToDate(date));
    }

    public void addEmailFilter(String desiredEmail){
        specs.add(new NoteSpecificationByEmail(desiredEmail));
    }

    public void addMessageFilter(String desiredMessage){
        specs.add(new NoteSpecificationByMessage(desiredMessage));
    }

    public void addSorted(){
        sorted=true;
    }

    public void restoreFilter(){
        specs=new NoteSpecificationConjunction();
        sorted=false;
    }

    public void onExit(){
        notepadDAO.onExit();
    }
}
