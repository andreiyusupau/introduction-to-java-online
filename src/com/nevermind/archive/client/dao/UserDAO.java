package com.nevermind.archive.client.dao;


import com.nevermind.archive.client.model.User;

import java.util.List;

public interface UserDAO {

    boolean create(User user);

    boolean login(String email, String hashedPassword);

     void  update(User user);

    void  delete(String email);
}
