package com.nevermind.library.dao;

import com.nevermind.library.model.role.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserDAO implements UserDAO {

    private final String fileName = "users.txt";
    private File file = new File(fileName);

    //создать пользователя
    @Override
    public void create(User user) {

        //если файл со списком пользователей отсутствует - создаем
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //для автоматического закрытия потока вывода будем использовать try-with-resources
        try (BufferedWriter bw
                     = new BufferedWriter(new FileWriter(file, true))) {
            String userStr;
            userStr = user.toString(); //преобразуем объект в строку

            //если запись не первая отступаем строку
            if (file.length() > 0) {
                bw.newLine();
            }
            bw.write(userStr);//записываем пользователя
        } catch (IOException io) {
            System.err.println("Ошибка при записи в файл");
        }
    }

    //считать пользователя по email
    @Override
    public User read(String email) throws NullPointerException {

//для автоматического закрытия потока ввода будем использовать try-with-resources
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] userDetails;
                userDetails = currLine.trim().split("/");
                if (userDetails[3].equals(email)) { //если email совпадает с заданным, возвращаем новый объект пользователя
                    return new User(userDetails[0], userDetails[1], userDetails[2],
                            userDetails[3], userDetails[4], userDetails[5].equals("true"));
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return null; //иначе возвращаем null
    }

    //считать всех пользователей
    @Override
    public List<User> readUsers() {
        List<User> users = new ArrayList<>();//создаем список для хранения всех пользователей
        //для автоматического закрытия потока ввода будем использовать try-with-resources
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            String currLine;
            //считываем всех пользователей и заносим в список
            while ((currLine = br.readLine()) != null) {
                User user;
                String[] userDetails;
                userDetails = currLine.trim().split("/");
                user = initUser(userDetails);
                users.add(user);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return users;
    }

    //считать всех администраторов
    @Override
    public List<User> readAdmins() {
        List<User> admins = new ArrayList<>(); //создаем список

        //для автоматического закрытия потока ввода будем использовать try-with-resources
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String currLine;

            //заносим пользователей в список при условии, что они являются администраторами
            while ((currLine = br.readLine()) != null) {
                User user;
                String[] userDetails;
                userDetails = currLine.trim().split("/");
                if (userDetails[5].equals("true")) {
                    user = initUser(userDetails);
                    admins.add(user);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return admins;
    }

    //вспомогательный метод для упрощеняи создания объекта пользователя из списка параметров
    private User initUser(String[] userDetails) {

        String firstName;
        firstName = userDetails[0];

        String middleName;
        middleName = userDetails[1];
        String lastName;
        lastName = userDetails[2];
        String email;
        email = userDetails[3];
        String hashedPassword;
        hashedPassword = userDetails[4];
        boolean isAdmin;
        isAdmin = userDetails[5].equals("true");

        return new User(firstName, middleName, lastName, email, hashedPassword, isAdmin);
    }


  @Override
  public void  update(User user){
    //В данном приложении не используется
     }

  @Override
    public void  delete(String email){
      //В данном приложении не используется
    }

}
