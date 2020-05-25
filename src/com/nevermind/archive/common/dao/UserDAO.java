package com.nevermind.archive.common.dao;


import com.nevermind.archive.client.model.User;

//интерфейс DAO для работы с пользователями. Необходим для унификации доступа со стороны контроллера независимо от типа базы данных
public interface UserDAO {

    boolean create(User user);

    boolean login(String email, String password);

    boolean checkUserRights(String email, String password);

     void  update(User user);

    void  delete(String email);
}
