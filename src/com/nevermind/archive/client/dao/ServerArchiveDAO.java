package com.nevermind.archive.client.dao;

import java.io.IOException;
import java.util.List;

import com.nevermind.archive.Message;
import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.client.model.Record;
import com.nevermind.archive.common.dao.ArchiveDAO;

public class ServerArchiveDAO implements ArchiveDAO {
private ArchiveClient client;
private RecordController rc;

    public ServerArchiveDAO() {
    }

    public void init(ArchiveClient client, RecordController rc) {
        this.client = client;
        this.rc = rc;
    }


    @Override
    public boolean create(Record record) {
        Message message;
        message= new Message("ADD_RECORD",record,rc.getUsername(),rc.getUserPassword());
        Message answer=null;
        try {
            answer= client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return Boolean.parseBoolean(answer.getMessage());
    }

    @Override
    public Record read(long id) {
        Message message;
        message= new Message("READ_RECORD",id,rc.getUsername(),rc.getUserPassword());
        Message answer=null;
        try {
            answer= client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        System.out.println("ANSWER"+(answer.getContent()).toString());
        return (Record) answer.getContent();
    }

    @Override
    public List<Record> readAll() {
        Message message;
        message= new Message("READ_ALL_RECORDS",null,rc.getUsername(),rc.getUserPassword());
        Message answer=null;
        try {
            answer= client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return (List<Record>) answer.getContent();
    }

    @Override
    public boolean update(Record record) {
        Message message;
        message= new Message("UPDATE_RECORD",record,rc.getUsername(),rc.getUserPassword());
        Message answer=null;
        try {
            answer= client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return Boolean.parseBoolean(answer.getMessage());
    }
}
