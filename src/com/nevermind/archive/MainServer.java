package com.nevermind.archive;

import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.common.dao.UserDAO;
import com.nevermind.archive.server.ArchiveServer;
import com.nevermind.archive.server.dao.XMLArchiveDAO;
import com.nevermind.archive.server.dao.XMLUserDAO;

import java.io.IOException;

public class MainServer {

    public static void main(String [] args){
        ArchiveServer server= new ArchiveServer();
        ArchiveDAO xmlArchiveDAO= new XMLArchiveDAO();
        UserDAO xmlUserDAO=new XMLUserDAO();
        server.init(xmlArchiveDAO,xmlUserDAO);
            //server.run();
    }

}
