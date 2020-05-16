package com.nevermind.archive.client.controller;


import com.nevermind.archive.client.dao.ServerUserDAO;
import com.nevermind.archive.client.dao.UserDAO;
import com.nevermind.archive.client.model.User;
import com.nevermind.archive.client.view.Menu;


public class UserController {

    private UserDAO userDAO;
    private Menu menu;
    private User currentUser;

    public UserController(Menu menu) {
        userDAO = new ServerUserDAO();
        this.menu = menu;
    }

    public String getUserPassword(){
        return   currentUser.getHashedPassword();
    }

    public String getUsername(){
        return currentUser.getEmail();
    }
    /*
    public boolean login(String email, String password) {
        User user;
        user = userDAO.read(email);
        if (user != null) {
            if (UserUtil.validatePassword(password, String.valueOf(user.getHashedPassword()))) {
                currentUser = user;
                return true;
            } else {
                System.err.println("Введен неверный пароль");
                return false;
            }
        } else {
            System.err.println("Пользователь с таким email не найден");
            return false;
        }
    }

    public void register(String firstName, String middleName, String lastName, String email, String password) {
        if (!(firstName.contains("/") || middleName.contains("/") || lastName.contains("/")
                || email.contains("/") || password.contains("/"))) {
            if (UserUtil.isEmailValid(email)) {
                String hashedPassword;
                hashedPassword = UserUtil.generatePasswordHash(password);
                if (hashedPassword != null) {
                    userDAO.create(new User(firstName, middleName, lastName, email, hashedPassword, false));
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

    public boolean isAdmin() {
        return currentUser.isAdmin();
    }

    public void notifyUsers(String text) {
        ArrayList<String> emailList = new ArrayList<>();
        for (User user : userDAO.readUsers()) {
            emailList.add(user.getEmail());
        }
        if (emailList.size() > 0) {
            UserUtil.sendMail(emailList, "В библиотеку добавлена книга", text);
        } else {
            System.err.println("В списке нет пользователей");
        }

    }

    public void notifyAdmins(String text) {
        ArrayList<String> emailList = new ArrayList<>();
        for (User admin : userDAO.readAdmins()) {
            emailList.add(admin.getEmail());
        }
        if (emailList.size() > 0) {
            UserUtil.sendMail(emailList, "Предложение добавить книгу", text);
        } else {
            System.err.println("В списке нет администраторов");
        }
    }
*/
}
