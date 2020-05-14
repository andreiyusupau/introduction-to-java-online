package com.nevermind.library.dao;

import com.nevermind.library.model.role.User;

import java.util.List;

public interface UserDAO {

    void create(User user);

    User read(String email);

    List<User> readUsers();

   List<User> readAdmins();

     void  update(User user);

    void  delete(String email);
}
