package com.nevermind.archive;

import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.common.dao.UserDAO;
import com.nevermind.archive.server.ArchiveServer;
import com.nevermind.archive.server.dao.XMLArchiveDAO;
import com.nevermind.archive.server.dao.XMLUserDAO;


public class MainServer {

    //инициализация и запуск серверной части
    public static void main(String [] args){
        ArchiveServer server= new ArchiveServer();
        ArchiveDAO xmlArchiveDAO= new XMLArchiveDAO();
        UserDAO xmlUserDAO=new XMLUserDAO();
        server.init(xmlArchiveDAO,xmlUserDAO);
    }

}
