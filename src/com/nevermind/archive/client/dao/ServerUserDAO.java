package com.nevermind.archive.client.dao;


import com.nevermind.archive.client.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerUserDAO implements UserDAO {

private final String fileName="users.txt";
 private File file = new File(fileName);

  @Override
  public void create(User user){

    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try( BufferedWriter bw
         = new BufferedWriter(new FileWriter(file, true))) {
      String userStr;
      userStr = user.toString();
      if (file.length() > 0) {
        bw.newLine();
      }
      bw.write(userStr);
    } catch (IOException io) {
      System.err.println("Ошибка при записи в файл");
    }
  }

  @Override
  public User read(String email) throws NullPointerException {

    try (BufferedReader br
                 = new BufferedReader(new FileReader(file))) {
      String currLine;
      while ((currLine = br.readLine()) != null) {
        String[] userDetails;
        userDetails = currLine.trim().split("/");
        if (userDetails[3].equals(email)) {
          return new User(userDetails[0], userDetails[1], userDetails[2],
                  userDetails[3], userDetails[4], userDetails[5].equals("true"));
        }
      }
    } catch (FileNotFoundException fnfe) {
      System.err.println("Файл не найден");
    } catch (IOException io) {
      System.err.println("Проблема чтения из файла");
    }
    return null;
  }

  @Override
  public List<User> readUsers() {
    List<User> users = new ArrayList<>();
    try(BufferedReader br
         = new BufferedReader(new FileReader(file))) {
      String currLine;
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

  @Override
  public List<User> readAdmins() {
    List<User> admins = new ArrayList<>();
    try(BufferedReader br
         = new BufferedReader(new FileReader(file))) {
      String currLine;
      while ((currLine = br.readLine()) != null) {
        User user;
        String[] userDetails;
        userDetails = currLine.trim().split("/");
        if(userDetails[5].equals("true")){
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


  public User initUser(String[] userDetails) {

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
