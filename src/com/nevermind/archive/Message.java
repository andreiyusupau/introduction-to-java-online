package com.nevermind.archive;

import java.io.Serializable;

public class Message implements Serializable {

    private String message;
    private Object content;
    private String username;
    private String hashedPass;

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
