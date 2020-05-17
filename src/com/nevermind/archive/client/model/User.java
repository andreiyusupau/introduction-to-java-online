package com.nevermind.archive.client.model;

import java.io.Serializable;

public class User implements Serializable {

    private String email;
    private String hashedPassword;
    private boolean admin;

    public User(String email, String hashedPassword,boolean admin) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.admin=admin;
    }

    @Override
    public String toString() {
        return email + "/" + hashedPassword+"/"+admin;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean isAdmin(){
        return admin;
    }

}
