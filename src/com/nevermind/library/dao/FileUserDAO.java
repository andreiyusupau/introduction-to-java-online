package com.nevermind.library.dao;


import com.nevermind.library.model.role.User;

public class FileUserDAO implements UserDAO {

  public void  create(User user){

  }

    public User read(String email,String password) throws NullPointerException{
    return null;//TODO
    }

    public void  update(User user){
    //В данном приложении не используется
     }

    public void  delete(String email){
      //В данном приложении не используется
    }

}
