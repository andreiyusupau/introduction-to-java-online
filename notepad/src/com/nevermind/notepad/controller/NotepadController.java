package com.nevermind.notepad.controller;

import com.nevermind.notepad.model.Note;
import com.nevermind.notepad.dao.FileNotepadDAO;
import com.nevermind.notepad.model.specs.*;
import com.nevermind.notepad.dao.NotepadDAO;
import com.nevermind.notepad.view.Notepad;

import java.time.LocalDate;
import java.util.ArrayList;

//контроллер
public class NotepadController {

    private NoteSpecificationConjunction specs = new NoteSpecificationConjunction(); //итоговый посиковый запрос
    private NotepadDAO notepadDAO; //ссылка на DAO
    private Notepad notepad; //ссылка на view
    private boolean sorted = false; //требуется ли отсортированный вывод

    //конструктор
    public NotepadController() {
        notepadDAO = new FileNotepadDAO();
        notepad = new Notepad(this);
        notepad.menu();
    }

    //добавить запись
    public boolean add(String title, String email, String message) {
return notepadDAO.add(title, email, message);
    }

    //получить все записи
    public ArrayList<Note> readAll(){
        return (ArrayList<Note>) notepadDAO.readAll();
    }

    //поиск по запросу
    public ArrayList<Note> search(){
return (ArrayList<Note>)notepadDAO.search(specs,sorted);
    }

    //добавить фильтр по названию
    public void addTitleFilter(String desiredTitle){
        specs.add(new NoteSpecificationByTitle(desiredTitle));
    }

    //добавить фильтр по дате (не ранее)
    public void addFromDateFilter(String desiredDate){
        LocalDate date;
        date=LocalDate.parse(desiredDate);
        specs.add(new NoteSpecificationFromDate(date));
    }

    //добавить фильтр по дате(не позднее)
    public void addToDateFilter(String desiredDate){
        LocalDate date;
        date=LocalDate.parse(desiredDate);
        specs.add(new NoteSpecificationToDate(date));
    }

    //добавить фильтр по email
    public void addEmailFilter(String desiredEmail){
        specs.add(new NoteSpecificationByEmail(desiredEmail));
    }

    //добавить фильтр по тексту сообщения
    public void addMessageFilter(String desiredMessage){
        specs.add(new NoteSpecificationByMessage(desiredMessage));
    }

    //указать необходимость сортировки
    public void addSorted(){
        sorted=true;
    }

    //сбросить фильтры
    public void restoreFilter(){
        specs=new NoteSpecificationConjunction();
        sorted=false;
    }

    //произвести действия при выходе (в данном случае запись в файл)
    public void onExit(){
        notepadDAO.onExit();
    }
}
