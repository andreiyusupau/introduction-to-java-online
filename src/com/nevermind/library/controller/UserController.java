package com.nevermind.library.controller;

import com.nevermind.library.dao.FileUserDAO;
import com.nevermind.library.model.role.User;
import com.nevermind.library.view.Menu;

public class UserController {

    private FileUserDAO userDAO;
    private Menu menu;
    private User currentUser;

    public void login(String email,String password){
currentUser=userDAO.read(email,password);
if(currentUser==null){
    System.out.println("Введенный email или пароль неверны");
menu.loginForm();
}else {
    //menu.catalogue();
}
    }

    public void register(String firstName,String middleName, String lastName,String email,String password){

    }


    public boolean isAdmin() {
        return true;
    }
}
