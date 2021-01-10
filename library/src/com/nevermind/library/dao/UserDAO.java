package com.nevermind.library.dao;

import com.nevermind.library.model.role.User;

import java.util.List;

//интерфейс DAO для работы с хранилищем пользователей. Необходим для унификации доступа со стороны контроллера независимо от типа базы данных
public interface UserDAO {

    void create(User user);

    User read(String email);

    List<User> readUsers();

    List<User> readAdmins();

    void  update(User user);

    void  delete(String email);
}
