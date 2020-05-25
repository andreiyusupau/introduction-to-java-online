package com.nevermind.archive;

import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.client.controller.UserController;
import com.nevermind.archive.client.dao.ServerArchiveDAO;
import com.nevermind.archive.client.dao.ServerUserDAO;
import com.nevermind.archive.client.view.Menu;
import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.common.dao.UserDAO;

/*Задание 3: создайте клиент-серверное приложение “Архив”.
Общие требования к заданию:
• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в
него изменения, или создать новое дело.
Требования к коду лабораторной работы:
• Для реализации сетевого соединения используйте сокеты.
• Формат хранения данных на сервере – xml-файлы.*/


/*Приложение реализовано с использованием архитектуры MVC
*
* Связь между классами выглядит следующим образом
* View <-> Controller <-> ServerDAO <-> Client <-> Server <-> XMLDAO*/

public class Main {

//инициализация и запуск клиентской части
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
        ((ServerUserDAO)userDAO).init(client);
        client.init(menu);
    }
}
