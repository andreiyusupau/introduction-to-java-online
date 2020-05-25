package com.nevermind.archive.client.dao;

import java.io.IOException;
import java.util.List;

import com.nevermind.archive.common.Message;
import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.client.model.Record;
import com.nevermind.archive.common.dao.ArchiveDAO;

//реализация dao для работы с сервером
public class ServerArchiveDAO implements ArchiveDAO {

    private ArchiveClient client;//клиентская часть приложения
    private RecordController rc; //контроллер

    public ServerArchiveDAO() {
    }

    //инициализация
    public void init(ArchiveClient client, RecordController rc) {
        this.client = client;
        this.rc = rc;
    }

//создать запись (создаем объект типа "сообщение" передаем через клиент на сервер)
    @Override
    public boolean create(Record record) {
        Message message;
        message = new Message("ADD_RECORD", record, rc.getUsername(), rc.getUserPassword());
        Message answer = null;
        try {
            answer = client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return Boolean.parseBoolean(answer.getMessage());
    }

    //получить запись по id (создаем объект типа "сообщение" передаем через клиент на сервер)
    @Override
    public Record read(long id) {
        Message message;
        message = new Message("READ_RECORD", id, rc.getUsername(), rc.getUserPassword());
        Message answer = null;
        try {
            answer = client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        System.out.println("ANSWER" + (answer.getContent()).toString());
        return (Record) answer.getContent();
    }

    //получить все записи (создаем объект типа "сообщение" передаем через клиент на сервер)
    @Override
    public List<Record> readAll() {
        Message message;
        message = new Message("READ_ALL_RECORDS", null, rc.getUsername(), rc.getUserPassword());
        Message answer = null;
        try {
            answer = client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return (List<Record>) answer.getContent();
    }

    //модифицировать запись (создаем объект типа "сообщение" передаем через клиент на сервер)
    @Override
    public boolean update(Record record) {
        Message message;
        message = new Message("UPDATE_RECORD", record, rc.getUsername(), rc.getUserPassword());
        Message answer = null;
        try {
            answer = client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return Boolean.parseBoolean(answer.getMessage());
    }
}
