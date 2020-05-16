package com.nevermind.archive.client.controller;

import com.nevermind.archive.client.model.User;

public class RecordController {
    UserController uc;


   public String getUserPassword(){
     return   uc.getUserPassword();
   }

    public String getUsername(){
       return uc.getUsername();
    }

}
