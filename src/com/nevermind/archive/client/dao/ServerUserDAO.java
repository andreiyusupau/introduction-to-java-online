package com.nevermind.archive.client.dao;

import com.nevermind.archive.common.Message;
import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.model.User;
import com.nevermind.archive.common.dao.UserDAO;

import java.io.IOException;

//реализация dao для работы с сервером
public class ServerUserDAO implements UserDAO {

    private ArchiveClient client;

    public ServerUserDAO() {
    }
//инициализация
    public void init(ArchiveClient client) {
        this.client = client;
    }

    //создать (создаем объект типа "сообщение" передаем через клиент на сервер)
    @Override
    public boolean create(User user) {
        Message message;
        System.out.println("СОЗДАЕМ СООБЩЕНИЕ");
        message = new Message("REGISTER_USER", user, null, null);
        try {
            Message answer;
            System.out.println("ПЫТАЕМСЯ ПОЛУЧИТЬ ОТВЕТ");
            answer = client.communicate(message);
            System.out.println("ВОЗВРАЩАЕМ ОТВЕТ");
            return Boolean.parseBoolean(answer.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return false;

    }
    //логин (создаем объект типа "сообщение" передаем через клиент на сервер)
    @Override
    public boolean login(String email, String password) {
        Message message;
        message = new Message("LOGIN_USER", null, email, password);
        Message answer = null;
        try {
            answer = client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return (boolean) answer.getContent();
    }

    @Override
    public boolean checkUserRights(String email, String password) {
        //Только для сервера
        return false;
    }


    @Override
    public void update(User user) {
        //В данном приложении не используется
    }

    @Override
    public void delete(String email) {
        //В данном приложении не используется
    }

}
