package com.nevermind.archive.client.controller;

import com.nevermind.archive.client.model.User;
import com.nevermind.archive.client.view.Menu;
import com.nevermind.archive.common.dao.ArchiveDAO;

public class RecordController {
    UserController uc;
Menu menu;
ArchiveDAO archiveDAO;

    public RecordController() {
    }

    public void init(UserController uc, Menu menu, ArchiveDAO archiveDAO) {
        this.uc = uc;
        this.menu = menu;
        this.archiveDAO = archiveDAO;
    }

    public String getUserPassword(){
     return   uc.getUserPassword();
   }

    public String getUsername(){
       return uc.getUsername();
    }

}
