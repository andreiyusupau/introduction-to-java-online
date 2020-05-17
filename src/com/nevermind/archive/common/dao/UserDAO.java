package com.nevermind.archive.common.dao;


import com.nevermind.archive.client.model.User;

import java.util.List;

public interface UserDAO {

    boolean create(User user);

    boolean login(String email, String password);

    boolean checkUserRights(String email, String password);

     void  update(User user);

    void  delete(String email);
}
