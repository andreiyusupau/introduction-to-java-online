package com.nevermind.archive;

import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.client.controller.UserController;
import com.nevermind.archive.client.dao.ServerArchiveDAO;
import com.nevermind.archive.client.dao.ServerUserDAO;
import com.nevermind.archive.client.view.Menu;
import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.common.dao.UserDAO;

public class Main {


    public static void main(String [] args){
        ArchiveClient client=new ArchiveClient();
        RecordController rc= new RecordController();
        UserController uc=new UserController();
        Menu menu=new Menu();
        ArchiveDAO archiveDAO=new ServerArchiveDAO();
        UserDAO userDAO=new ServerUserDAO();
        rc.init(uc,menu,archiveDAO);
        uc.init(userDAO,menu);
        menu.init(uc,rc);
        ((ServerArchiveDAO)archiveDAO).init(client,rc);
        ((ServerUserDAO)userDAO).init(client,uc);
        client.init(menu);



    }
}
