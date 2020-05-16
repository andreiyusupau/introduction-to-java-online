package com.nevermind.archive.client;

import com.nevermind.archive.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ArchiveClient {

    private  InetAddress host;
    private Socket  socket;
    private  ObjectOutputStream oos;
    private  ObjectInputStream ois;

    public void init() {

        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
           socket = new Socket(host.getHostName(), 9876);
           oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ioe) {
            System.err.println("ioe");
        }
    }

    public Message communicate(Message message) throws IOException, ClassNotFoundException {
         oos.writeObject(message);
        return (Message) ois.readObject();
    }
}
