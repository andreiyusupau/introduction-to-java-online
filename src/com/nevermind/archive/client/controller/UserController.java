package com.nevermind.archive.client.controller;


import com.nevermind.archive.common.util.Util;
import com.nevermind.archive.common.dao.UserDAO;
import com.nevermind.archive.client.model.User;
import com.nevermind.archive.client.view.Menu;

//контроллер пользователей
public class UserController {

    private UserDAO userDAO; //ссылка на dao
    private Menu menu; //ссылка на view
    private User currentUser; //текущий пользвоатель

    public UserController() {
    }

    //инициализация
    public void init(UserDAO userDAO, Menu menu) {
        this.userDAO = userDAO;
        this.menu = menu;
    }

    //получить пароль
    public String getUserPassword(){
        return   currentUser.getHashedPassword();
    }

    // получить имя пользователя
    public String getUsername(){
        return currentUser.getEmail();
    }

    //логин
    public boolean login(String email, String password) {

        if(userDAO.login(email,password)){
            currentUser = new User(email,password,false);
            return true;
        } else {
            System.err.println("Пользователь с таким email и паролем не найден");
            return false;
        }
    }

    //регистрация
    public void register(String email, String password) {

            if (Util.isEmailValid(email)) {
                String hashedPassword;
                hashedPassword = Util.generatePasswordHash(password); //хешируем пароль
                if (hashedPassword != null) {
                    userDAO.create(new User(email, hashedPassword,false)); //создаем пользователя
                    hashedPassword = null;
                    System.out.println("Пользователь успешно добавлен.");
                    menu.loginForm();
                } else {
                    System.err.println("Не удалось создать пароль");
                    menu.enterMenu();
                }
            } else {
                System.err.println("Введенный email не соответствует шаблону");
                menu.enterMenu();
            }
    }

}
