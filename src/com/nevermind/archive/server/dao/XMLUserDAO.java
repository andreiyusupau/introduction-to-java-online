package com.nevermind.archive.server.dao;

import com.nevermind.archive.client.model.User;
import com.nevermind.archive.common.util.Util;
import com.nevermind.archive.common.dao.UserDAO;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class XMLUserDAO implements UserDAO {

    private final String fileName = "users.xml";

    public XMLUserDAO() {
    }


    @Override
    public boolean create(User user) {

        File file = new File(fileName);
        boolean flag;
        flag = !file.exists(); //если файл не существует

        //создаем потоки ввода/вывода
        XMLEventReader reader = null;
        XMLEventWriter writer = null;
        FileOutputStream os = null;
        FileInputStream is = null;
        try {
            //создаем фабрики ввода/вывода и событий
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            //если файл базы данных отсутствует (fileOutputStream создаст новый файл)
            if (flag) {
                os = new FileOutputStream(file);
                writer = xmlOutputFactory.createXMLEventWriter(os);
                StartDocument startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
                writer.add(startDocument);
                Characters space;
                space = eventFactory.createSpace("\n");
                writer.add(space);
                StartElement startElement = eventFactory.createStartElement(new QName("users"), null, null);
                writer.add(startElement);
                writer.add(space);

                addUser(writer, eventFactory, user, 0);

                writer.add(space);
                EndElement endElement = eventFactory.createEndElement(new QName("users"), null);
                writer.add(endElement);
                EndDocument endDocument = eventFactory.createEndDocument();
                writer.add(endDocument);

                os.close();
                writer.close();
            } else { //если файл существует, переписываем его содержимое в temp.xml добавляем туда новую запись
                File temp = new File("temp.xml");
                os = new FileOutputStream(temp);
                writer = xmlOutputFactory.createXMLEventWriter(os);
                is = new FileInputStream(file);
                reader = xmlInputFactory.createXMLEventReader(is);
                long currId = 0;
                while (reader.hasNext()) {
                    XMLEvent nextEvent = reader.nextEvent();
                    Characters space;
                    space = eventFactory.createSpace("\n");

                    if (nextEvent.isStartElement() && nextEvent.asStartElement().getName().getLocalPart().equals("user")) {
                        currId = Long.parseLong(nextEvent.asStartElement().getAttributeByName(new QName("id")).getValue());
                    }
                    if (nextEvent.isEndElement()) {
                        EndElement endElement = nextEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("users")) {
                            addUser(writer, eventFactory, user, currId + 1);

                            writer.add(space);
                            writer.add(endElement);
                        } else {
                            writer.add(nextEvent);
                        }
                    } else {
                        writer.add(nextEvent);
                        if (nextEvent.isStartDocument()) {
                            writer.add(space);
                        }
                    }
                }
                is.close();
                reader.close();
                os.close();
                writer.close();

                //заменяем содержимое файла file.xml содержимым файла temp.xml
                try {
                    Files.move(Path.of(temp.toURI()), Path.of(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Ошибка при замене файла");
                }

            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Файл не найден");
        }  catch (XMLStreamException e) {
            System.err.println("Ошибка при закрытии потока записи в XML");
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии потока записи");
        } finally {
            try {
                //закрываем все потоки
                if (is != null)
                    is.close();
                if (reader != null)
                    reader.close();
                if (os != null)
                    os.close();
                if (writer != null)
                    writer.close();
            }  catch (XMLStreamException e) {
                System.err.println("Ошибка при закрытии потока записи в XML");
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии потока записи");
            }
        }
        return false;
    }

    //метод для упрощения записи дела
    private void addUser(XMLEventWriter writer, XMLEventFactory eventFactory, User user, long currId) throws XMLStreamException {

        StartElement startElement;
        startElement = eventFactory.createStartElement(new QName("user"), null, null);
        writer.add(startElement);
        Attribute attribute;
        attribute = eventFactory.createAttribute("id", String.valueOf(currId));
        writer.add(attribute);
        Characters space;
        space = eventFactory.createSpace("\n");
        writer.add(space);

        addNode(writer, eventFactory, "email", user.getEmail());
        writer.add(space);
        addNode(writer, eventFactory, "password", user.getHashedPassword());
        writer.add(space);
        addNode(writer, eventFactory, "admin", String.valueOf(user.isAdmin()));
        writer.add(space);
        EndElement endElement;
        endElement = eventFactory.createEndElement(new QName("user"), null);
        writer.add(endElement);
    }

    //метод для упрощения записи
    private void addNode(XMLEventWriter writer, XMLEventFactory eventFactory, String localName, String recordPart) throws XMLStreamException {
        QName firstName = new QName(localName);
        StartElement startElement = eventFactory.createStartElement(firstName, null, null);
        writer.add(startElement);

        Characters content = eventFactory.createCharacters(recordPart);
        writer.add(content);
        EndElement endElement = eventFactory.createEndElement(firstName, null);
        writer.add(endElement);
    }

    //логин (поочередно сверяем введенные пароль и логин с существующими в базе)
    @Override
    public boolean login(String email, String password) {
        XMLEventReader reader = null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            String storedPassword=null;
            String storedEmail=null;
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("email")) {
                       storedEmail=reader.nextEvent().asCharacters().getData();
                    }else if(startElement.getName().getLocalPart().equals("password")){
                        storedPassword=reader.nextEvent().asCharacters().getData();
                    }
                }
                if(nextEvent.isEndElement()){
                    EndElement endElement=nextEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("user")){
                        if (storedEmail!=null&&
                                storedEmail.equals(email) &&
                                storedPassword!=null&&
                                Util.validatePassword(password, storedPassword)) {
                            System.out.println("LOGIN SUCCESS ");
                            return true;
                        }
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Файл не найден");
        } catch (XMLStreamException xmlStreamException) {
            System.err.println("Ошибка при чтении файла");
        } finally {
            try {
                //закрываем поток
                if (reader != null)
                    reader.close();
            } catch (XMLStreamException e) {
                System.err.println("Ошибка при закрытии потока чтения XML файла");
            }
        }
        return false;
    }

//проверка прав пользователя
public boolean checkUserRights(String email, String password){
    XMLEventReader reader = null;
    try {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

        String storedPassword=null;
        String storedEmail=null;
        boolean rights=false;
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "email" -> storedEmail =reader.nextEvent().asCharacters().getData();
                    case "password" -> storedPassword = reader.nextEvent().asCharacters().getData();
                    case "admin" -> rights = Boolean.parseBoolean(reader.nextEvent().asCharacters().getData());
                }
            }
            if(nextEvent.isEndElement()){
                EndElement endElement=nextEvent.asEndElement();
                if(endElement.getName().getLocalPart().equals("user")){
                    if (rights&&
                            storedEmail!=null&&
                            storedEmail.equals(email) &&
                            storedPassword!=null&&
                            Util.validatePassword(password, storedPassword)) {
                        return true;
                    }
                }
            }
        }
    } catch (FileNotFoundException fileNotFoundException) {
        System.err.println("Файл не найден");
    } catch (XMLStreamException xmlStreamException) {
        System.err.println("Ошибка при чтении файла");
    } finally {
        try {
            //закрываем поток
            if (reader != null)
                reader.close();
        } catch (XMLStreamException e) {
            System.err.println("Ошибка при закрытии потока чтения XML файла");
        }
    }
    return false;
}

    @Override
    public void update(User user) {
        //В данном приложении не используется
    }

    @Override
    public void delete(String email) {
        //В данном приложении не используется
    }

}
