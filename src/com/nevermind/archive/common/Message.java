package com.nevermind.archive.common;

import java.io.Serializable;

//класс-обертка для обмена информацией между клиентом и сервером
public class Message implements Serializable {

    private final String message; //текст сообщения(согласно протоколу обмена сообщениямимежду клиентом и сервером)
    private final Object content; //содержимое сообщения
    private final String username; //логин (для проведения операций требующих права доступа)
    private final String hashedPass; //пароль (для проведения операций требующих права доступа)

    public Message(String message, Object content, String username, String hashedPass) {
        this.message = message;
        this.content = content;
        this.username = username;
        this.hashedPass =hashedPass;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPass() {
        return hashedPass;
    }
}
