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

    private InetAddress host; //адрес хоста
    private Socket socket; //сокет
    private ObjectOutputStream oos; //поток вывода объектов
    private ObjectInputStream ois; //поток ввода объектов
    private Menu menu;


    public void init(Menu menu) {
        this.menu = menu;
        try {
            host = InetAddress.getLocalHost(); //в качестве хоста используем localhost
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket = new Socket(host.getHostName(), 9876); //создаем сокет для порта 9876
            oos = new ObjectOutputStream(socket.getOutputStream()); //создаем поток вывода
            ois = new ObjectInputStream(socket.getInputStream()); //создаем поток ввода

            menu.enterMenu(); //переходим в меню
        } catch (IOException ioe) {
            System.err.println("ioe");
        } finally { //в конце закрываем сокет и потоки
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

    //метод для отправки сообщения и получения ответа от сервера
    public Message communicate(Message message) throws IOException, ClassNotFoundException {
        System.out.println("SEND MESSAGE");
        oos.writeObject(message);
        System.out.println("GET MESSAGE");
        return (Message) ois.readObject();
    }
}
