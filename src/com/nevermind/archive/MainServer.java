package com.nevermind.archive;

import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.server.ArchiveServer;

public class MainServer {

    public static void main(String [] args){
        ArchiveServer server= new ArchiveServer();
        server.init();
    }

}
