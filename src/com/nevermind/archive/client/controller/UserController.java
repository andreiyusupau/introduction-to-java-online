package com.nevermind.archive.client.controller;


import com.nevermind.archive.common.util.Util;
import com.nevermind.archive.common.dao.UserDAO;
import com.nevermind.archive.client.model.User;
import com.nevermind.archive.client.view.Menu;


public class UserController {

    private UserDAO userDAO;
    private Menu menu;
    private User currentUser;

    public UserController() {
    }

    public void init(UserDAO userDAO, Menu menu) {
        this.userDAO = userDAO;
        this.menu = menu;
    }

    public String getUserPassword(){
        return   currentUser.getHashedPassword();
    }

    public String getUsername(){
        return currentUser.getEmail();
    }

    public boolean login(String email, String password) {

        if(userDAO.login(email,password)){
            currentUser = new User(email,password,false);
            return true;
        } else {
            System.err.println("Пользователь с таким email и паролем не найден");
            return false;
        }
    }

    public void register(String email, String password) {
        if (!password.contains("/")) {
            if (Util.isEmailValid(email)) {
                String hashedPassword;
                hashedPassword = Util.generatePasswordHash(password);
                if (hashedPassword != null) {
                    System.out.println("CREATE USER");
                    userDAO.create(new User(email, hashedPassword,false));
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

        } else {
            System.err.println("Введенные данные не должны содержать знак \"/\"");
            menu.enterMenu();
        }
    }

}
