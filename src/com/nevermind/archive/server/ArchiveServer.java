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

public class ArchiveServer {

    private final int port = 9876;
    private ServerSocket server;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ArchiveDAO archiveDAO;
    private UserDAO userDAO;

    public void init(ArchiveDAO archiveDAO,UserDAO userDAO) {
        this.archiveDAO=archiveDAO;
        this.userDAO=userDAO;
        try {
            server = new ServerSocket(port);
            socket = server.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
       run();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        } finally {
           try {
               System.out.println("SERVER CLOSE");


            ois.close();
            oos.close();
               socket.close();
               server.close();} catch (IOException e) {
               e.printStackTrace();
           }
        }

    }

    public void run() throws IOException, ClassNotFoundException {
        while (!socket.isClosed()) {
            System.out.println("READ");
            Message message;
                message = (Message) ois.readObject();
            System.out.println("ANSWER");
            Message answer = processMessage(message);
            oos.writeObject(answer);
        }
    }

    public Message processMessage(Message message) {
        Message answer;
        answer = switch (message.getMessage()) {
            case "ADD_RECORD" ->{
                if (userDAO.checkUserRights(message.getUsername(), message.getHashedPass())) {
                    yield new Message(message.getMessage(), archiveDAO.create((Record) message.getContent()), null, null);
                }else {
                    yield  new Message(message.getMessage(), null, null, null);
                }
            }
            case "READ_RECORD" -> new Message(message.getMessage(), archiveDAO.read((long) message.getContent()), null, null);
            case "READ_ALL_RECORDS" -> new Message(message.getMessage(), archiveDAO.readAll(), null, null);
            case "UPDATE_RECORD" -> {
                if (userDAO.checkUserRights(message.getUsername(), message.getHashedPass())) {
                    yield new Message(message.getMessage(), archiveDAO.update((Record) message.getContent()), null, null);
                }else {
                yield  new Message(message.getMessage(), null, null, null);
                }
            }
            case "LOGIN_USER" -> new Message(message.getMessage(), userDAO.login(message.getUsername(), message.getHashedPass()), null, null);
            case "REGISTER_USER" -> new Message(message.getMessage(), userDAO.create((User) message.getContent()), null, null);
            default -> null;
        };

        return answer;
    }
}