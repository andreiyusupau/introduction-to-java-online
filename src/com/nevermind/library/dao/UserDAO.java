package com.nevermind.library.dao;

import com.nevermind.library.model.role.User;

public interface UserDAO {

    public void create(User user);

    public User read(String email, String password);

    public void  update(User user);

    public void  delete(String email);
}
