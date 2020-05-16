package com.nevermind.archive.client.dao;


import com.nevermind.archive.client.model.User;

import java.util.List;

public interface UserDAO {

    void create(User user);

    User read(String email);

    List<User> readUsers();

   List<User> readAdmins();

     void  update(User user);

    void  delete(String email);
}
