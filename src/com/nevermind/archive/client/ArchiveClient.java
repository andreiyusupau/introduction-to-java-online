package com.nevermind.archive.client;

import com.nevermind.archive.common.Message;
import com.nevermind.archive.client.view.Menu;

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
private Menu menu;



    public void init(Menu menu) {
this.menu=menu;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
           socket = new Socket(host.getHostName(), 9876);
           oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            menu.enterMenu();
        } catch (IOException ioe) {
            System.err.println("ioe");
        } finally {
            try {
                System.out.println("CLIENT CLOSE");

                ois.close();
                oos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Message communicate(Message message) throws IOException, ClassNotFoundException {
        System.out.println("SEND MESSAGE");
         oos.writeObject(message);
        System.out.println("GET MESSAGE");
        return  (Message) ois.readObject();
    }
}
