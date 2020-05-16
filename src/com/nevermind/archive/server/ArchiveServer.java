package com.nevermind.archive.server;


import com.nevermind.archive.Message;

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

    public void init(){

        try{
            server = new ServerSocket(port);
          socket = server.accept();
           ois = new ObjectInputStream(socket.getInputStream());
           oos = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException ioe) {
            System.err.println("Ошибка при передаче данных");
        }

    }

    public void run() throws IOException, ClassNotFoundException {
        while(true){
            Message message = (Message) ois.readObject();
            Message answer=processMessage(message);
            oos.writeObject(answer);
        }
    }

    public Message processMessage(Message message){
        return null;
    }
}