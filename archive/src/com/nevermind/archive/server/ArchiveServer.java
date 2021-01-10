package com.nevermind.archive.server;


import com.nevermind.archive.common.Message;
import com.nevermind.archive.client.model.Record;
import com.nevermind.archive.client.model.User;
import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.common.dao.UserDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

//сервер
public class ArchiveServer {

    private final int port = 9876;//порт
    private ServerSocket server; //сокет сервера
    private Socket socket; //сокет
    private ObjectInputStream ois; //поток ввода
    private ObjectOutputStream oos; // поток вывода
    private ArchiveDAO archiveDAO;
    private UserDAO userDAO;

    public void init(ArchiveDAO archiveDAO, UserDAO userDAO) {
        this.archiveDAO = archiveDAO;
        this.userDAO = userDAO;
        try {
            server = new ServerSocket(port); //создаем новый серверный сокет
            socket = server.accept(); //создаем соединение
            oos = new ObjectOutputStream(socket.getOutputStream()); //создаем поток вывода
            ois = new ObjectInputStream(socket.getInputStream()); //создаем поток ввода
            run();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        } finally {
            try {
                System.out.println("SERVER CLOSE");

                //закрываем потоки и сокеты
                ois.close();
                oos.close();
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //работа сервера
    public void run() throws IOException, ClassNotFoundException {
        while (!socket.isClosed()) {//пока есть соединение
            System.out.println("READ");

            Message message;
            message = (Message) ois.readObject();//ждем сообщение

            System.out.println("ANSWER");

            Message answer = processMessage(message); //формируем ответ

            oos.writeObject(answer); //отвечаем
        }
    }

    //формирование ответа
    public Message processMessage(Message message) {
        Message answer;
        answer = switch (message.getMessage()) {
            case "ADD_RECORD" -> { //добавить дело
                if (userDAO.checkUserRights(message.getUsername(), message.getHashedPass())) {
                    yield new Message(message.getMessage(), archiveDAO.create((Record) message.getContent()), null, null);
                } else {
                    yield new Message(message.getMessage(), null, null, null);
                }
            }
            //получить дело
            case "READ_RECORD" -> new Message(message.getMessage(), archiveDAO.read((long) message.getContent()), null, null);

            //получить все дела
            case "READ_ALL_RECORDS" -> new Message(message.getMessage(), archiveDAO.readAll(), null, null);

            //обновить дело (с проверкой прав)
            case "UPDATE_RECORD" -> {
                if (userDAO.checkUserRights(message.getUsername(), message.getHashedPass())) {
                    yield new Message(message.getMessage(), archiveDAO.update((Record) message.getContent()), null, null);
                } else {
                    yield new Message(message.getMessage(), null, null, null);
                }
            }

            //логин
            case "LOGIN_USER" -> new Message(message.getMessage(), userDAO.login(message.getUsername(), message.getHashedPass()), null, null);

            //регистрация
            case "REGISTER_USER" -> new Message(message.getMessage(), userDAO.create((User) message.getContent()), null, null);

            //если сообщение не соответствует протоколу - null
            default -> null;
        };

        return answer;
    }
}