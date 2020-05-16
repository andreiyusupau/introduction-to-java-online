package com.nevermind.archive.client.dao;

import com.nevermind.archive.Message;
import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.UserController;
import com.nevermind.archive.client.model.User;

import java.io.IOException;

public class ServerUserDAO implements UserDAO {

    private ArchiveClient client;
    private UserController uc;

  @Override
  public boolean create(User user){
      Message message;
      message= new Message("REGISTER_USER",user,null,null);
      Message answer=null;
      try {
          answer= client.communicate(message);
      } catch (IOException e) {
          System.err.println("Ошибка при обработке файла");
      } catch (ClassNotFoundException e) {
          System.err.println("Класс не найден");
      }
      return Boolean.parseBoolean(answer.getMessage());
  }

    @Override
    public boolean login(String email, String hashedPassword) {
        Message message;
        message= new Message("LOGIN_USER",null,email,hashedPassword);
        Message answer=null;
        try {
            answer= client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }
        return Boolean.parseBoolean(answer.getMessage());
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
