package com.nevermind.library.controller;

import com.nevermind.library.dao.FileUserDAO;
import com.nevermind.library.dao.UserDAO;
import com.nevermind.library.model.role.User;
import com.nevermind.library.util.UserUtil;
import com.nevermind.library.view.Menu;
import java.util.ArrayList;

//контроллер рабоыт с пользователями
public class UserController {

    private UserDAO userDAO; //ссылка на DAO
    private Menu menu; //ссылка на view
    private User currentUser; //текущий пользователь

    //конструктор
    public UserController(Menu menu) {
        userDAO = new FileUserDAO();
        this.menu = menu;
    }

    //вход
    public boolean login(String email, String password) {
        User user;
        user = userDAO.read(email); //проверяем существует ли пользователь с таким email
        if (user != null) { //если да- проверяем соответствие хешей паролей

            if (UserUtil.validatePassword(password, String.valueOf(user.getHashedPassword()))) {

                //если пароли совпадают устанавливаем данного пользователя в качестве текущего
                currentUser = user;
                return true;
            } else {
                System.err.println("Введен неверный пароль");
                return false;
            }
        } else { //если нет - отказываем во входе
            System.err.println("Пользователь с таким email не найден");
            return false;
        }
    }

    //регистрация нового пользователя
    public void register(String firstName, String middleName, String lastName, String email, String password) {

        //так как символ "/" служит разделителем в текстовом файле, убеждаемся. что он не содержится в ФИО пользователя.
        //email проверяется отдельно, пароль хешируется, поэтому их проверять не нужно
        if (!(firstName.contains("/") || middleName.contains("/") || lastName.contains("/"))) {

            if (UserUtil.isEmailValid(email)) { //проверяем валидность email
                String hashedPassword;
                hashedPassword = UserUtil.generatePasswordHash(password); //хешируем пароль

                /*если успешно - создаем нового пользователя
                (права администратора не присваиваются, на данном этапе их присвоение происходит через ручное
                редактирование файла с пользователями, но для удобства может быть создана консоль администратора
                для управления всеми правами)*/
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

    //проверка является ли пользователь администратором
    public boolean isAdmin() {
        return currentUser.isAdmin();
    }

    //уведомление пользователей (о добавлении новой книги в каталог)
    public void notifyUsers(String text) {
        ArrayList<String> emailList = new ArrayList<>();

        //создаем список email пользователей
        for (User user : userDAO.readUsers()) {
            emailList.add(user.getEmail());
        }

        //отправляем им сообщение о новой книге
        if (emailList.size() > 0) {
            UserUtil.sendMail(emailList, "В библиотеку добавлена книга ", text);
        } else {
            System.err.println("В списке нет пользователей");
        }

    }

    //уведомление администраторов (о предложении добавить новую книгу)
    public void notifyAdmins(String text) {
        ArrayList<String> emailList = new ArrayList<>();

        //создаем список email администраторов
        for (User admin : userDAO.readAdmins()) {
            emailList.add(admin.getEmail());
        }

        //отправляем им сообщение с предложением добавить новую книгу
        if (emailList.size() > 0) {
            UserUtil.sendMail(emailList, "Предложение добавить книгу", text);
        } else {
            System.err.println("В списке нет администраторов");
        }
    }
}
