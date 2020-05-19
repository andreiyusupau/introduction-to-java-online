package com.nevermind.archive.client.controller;

import com.nevermind.archive.client.model.Record;
import com.nevermind.archive.client.view.Menu;
import com.nevermind.archive.common.dao.ArchiveDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class RecordController {
    UserController uc;
Menu menu;
ArchiveDAO archiveDAO;

    public RecordController() {
    }

    public void init(UserController uc, Menu menu, ArchiveDAO archiveDAO) {
        this.uc = uc;
        this.menu = menu;
        this.archiveDAO = archiveDAO;
    }

    public String getUserPassword(){
     return   uc.getUserPassword();
   }

    public String getUsername(){
       return uc.getUsername();
    }

    public boolean add(String firstName, String middleName, String lastName, String dateOfBirth,int status, int yearOfEntry, int yearOfGraduation, String description){
       return archiveDAO.create(new Record(firstName,middleName,lastName,LocalDate.parse(dateOfBirth),status,yearOfEntry,yearOfGraduation,description));
    }

    public Record read(long id){
return archiveDAO.read(id);
    }

    public ArrayList<Record> readAll(){
return(ArrayList<Record>) archiveDAO.readAll();
    }

    public boolean update(long id,String firstName, String middleName, String lastName, String dateOfBirth,int status, int yearOfEntry, int yearOfGraduation, String description){
Record record=new Record(firstName,middleName,lastName,LocalDate.parse(dateOfBirth),status,yearOfEntry,yearOfGraduation,description);
record.setId(id);
        return archiveDAO.update(record);
    }


}
