package com.nevermind.archive.server.dao;

import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.client.model.Record;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLArchiveDAO implements ArchiveDAO {

    private final String fileName = "archive.xml";

    public XMLArchiveDAO() {
    }

    @Override
    public boolean create(Record record) {

        File file = new File(fileName);
        boolean flag;
        flag = !file.exists();


        XMLEventReader reader = null;
        XMLEventWriter writer = null;
        FileOutputStream os = null;
        FileInputStream is = null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            if (flag) {
                os = new FileOutputStream(file);
                writer = xmlOutputFactory.createXMLEventWriter(os);
                StartDocument startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
                writer.add(startDocument);
                Characters space;
                space = eventFactory.createSpace("\n");
                writer.add(space);
                StartElement startElement = eventFactory.createStartElement(new QName("students"), null, null);
                writer.add(startElement);
                writer.add(space);

                addRecord(writer, eventFactory, record, 0);

                writer.add(space);
                EndElement endElement = eventFactory.createEndElement(new QName("students"), null);
                writer.add(endElement);
                EndDocument endDocument = eventFactory.createEndDocument();
                writer.add(endDocument);

                os.close();
                writer.close();
            } else {
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

                    if (nextEvent.isStartElement() && nextEvent.asStartElement().getName().getLocalPart().equals("student")) {
                        currId = Long.parseLong(nextEvent.asStartElement().getAttributeByName(new QName("id")).getValue());
                        System.out.println("change currid " + currId);
                    }
                    if (nextEvent.isEndElement()) {
                        EndElement endElement = nextEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("students")) {
                            addRecord(writer, eventFactory, record, currId + 1);

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

    private void addRecord(XMLEventWriter writer, XMLEventFactory eventFactory, Record record, long currId) throws XMLStreamException {

        StartElement startElement;
        startElement = eventFactory.createStartElement(new QName("student"), null, null);
        writer.add(startElement);
        Attribute attribute;
        attribute = eventFactory.createAttribute("id", String.valueOf(currId));
        writer.add(attribute);
        Characters space;
        space = eventFactory.createSpace("\n");
        writer.add(space);

        addNode(writer, eventFactory, "firstName", record.getFirstName());
        writer.add(space);
        addNode(writer, eventFactory, "middleName", record.getMiddleName());
        writer.add(space);
        addNode(writer, eventFactory, "lastName", record.getLastName());
        writer.add(space);
        addNode(writer, eventFactory, "dateOfBirth", record.getDateOfBirth().toString());
        writer.add(space);
        addNode(writer, eventFactory, "yearOfEntry", String.valueOf(record.getYearOfEntry()));
        writer.add(space);
        addNode(writer, eventFactory, "yearOfGraduation", String.valueOf(record.getYearOfGraduation()));
        writer.add(space);
        addNode(writer, eventFactory, "description", record.getDescription());
        writer.add(space);
        EndElement endElement1;
        endElement1 = eventFactory.createEndElement(new QName("student"), null);
        writer.add(endElement1);
    }

    public void addNode(XMLEventWriter writer, XMLEventFactory eventFactory, String localName, String recordPart) throws XMLStreamException {
        QName firstName = new QName(localName);
        StartElement startElement = eventFactory.createStartElement(firstName, null, null);
        writer.add(startElement);

        Characters content = eventFactory.createCharacters(recordPart);
        writer.add(content);
        EndElement endElement = eventFactory.createEndElement(firstName, null);
        writer.add(endElement);
    }

    @Override
    public Record read(long id) {
        XMLEventReader reader = null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            Record record = null;
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("student") &&
                            Long.parseLong(startElement.getAttributeByName(new QName("id")).getValue()) == id) {
                        record = new Record();
                        readRecord(reader, nextEvent, record);
                    }

                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("student")) {
                        return record;
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Файл не найден");
        } catch (XMLStreamException xmlStreamException) {
            System.err.println("Ошибка при чтении файла");
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (XMLStreamException e) {
                System.err.println("Ошибка при закрытии потока чтения XML файла");
            }
        }
        return null;
    }

    public void readRecord(XMLEventReader reader, XMLEvent nextEvent, Record record) throws XMLStreamException {
        record.setId(Long.parseLong(nextEvent.asStartElement().getAttributeByName(new QName("id")).getValue()));
        boolean flag = true;
        while (reader.hasNext() && flag) {
            nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "firstName" -> {
                        nextEvent = reader.nextEvent();
                        record.setFirstName(nextEvent.asCharacters().getData());
                    }
                    case "middleName" -> {
                        nextEvent = reader.nextEvent();
                        record.setMiddleName(nextEvent.asCharacters().getData());
                    }
                    case "lastName" -> {
                        nextEvent = reader.nextEvent();
                        record.setLastName(nextEvent.asCharacters().getData());
                    }
                    case "dateOfBirth" -> {
                        nextEvent = reader.nextEvent();
                        record.setDateOfBirth(LocalDate.parse(nextEvent.asCharacters().getData()));
                    }
                    case "yearOfEntry" -> {
                        nextEvent = reader.nextEvent();
                        record.setYearOfEntry(Integer.parseInt(nextEvent.asCharacters().getData()));
                    }
                    case "yearOfGraduation" -> {
                        nextEvent = reader.nextEvent();
                        record.setYearOfGraduation(Integer.parseInt(nextEvent.asCharacters().getData()));
                    }
                    case "description" -> {
                        nextEvent = reader.nextEvent();
                        record.setDescription(nextEvent.asCharacters().getData());
                        flag = false;
                    }
                }
            }
        }
    }


    @Override
    public List<Record> readAll() {
        XMLEventReader reader = null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            List<Record> records = new ArrayList<>();
            Record record = null;
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("student")) {
                        record = new Record();
                        readRecord(reader, nextEvent, record);
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("student")) {
                        records.add(record);
                    }
                }
            }
            return records;
        } catch (FileNotFoundException e) {
            System.err.println("Файл не нйден");
            return null;
        } catch (XMLStreamException e) {
            System.err.println("Ошибка при чтении из файла");
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (XMLStreamException e) {
                System.err.println("Ошибка при закрытии потока чтения из файла");
            }
        }

    }

    @Override
    public boolean update(Record record) {
        long targetId = record.getId();
        File file = new File(fileName);
        boolean flag;
        flag = !file.exists();
        XMLEventReader reader = null;
        XMLEventWriter writer = null;
        FileOutputStream os = null;
        FileInputStream is = null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            File temp = new File("temp.xml");

            is = new FileInputStream(file);
            reader = xmlInputFactory.createXMLEventReader(is);
            os = new FileOutputStream(temp);
            writer = xmlOutputFactory.createXMLEventWriter(os);

            if (flag) {
                StartDocument startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
                writer.add(startDocument);
                Characters space;
                space = eventFactory.createSpace("\n");
                writer.add(space);
                StartElement startElement = eventFactory.createStartElement(new QName("students"), null, null);
                writer.add(startElement);
                writer.add(space);

                addRecord(writer, eventFactory, record, 0);

                writer.add(space);
                EndElement endElement = eventFactory.createEndElement(new QName("students"), null);
                writer.add(endElement);
                EndDocument endDocument = eventFactory.createEndDocument();
                writer.add(endDocument);
            } else {
                long currId = 0;
                while (reader.hasNext()) {
                    XMLEvent nextEvent = reader.nextEvent();
                    if (nextEvent.isStartElement() && nextEvent.asStartElement().getName().getLocalPart().equals("student")) {
                        currId = Long.parseLong(nextEvent.asStartElement().getAttributeByName(new QName("id")).getValue());
                        if (currId == targetId) {
                            addRecord(writer, eventFactory, record, currId);
                            Characters space;
                            space = eventFactory.createSpace("\n");
                            writer.add(space);
                            while (!(nextEvent.isEndElement() && nextEvent.asEndElement().getName().getLocalPart().equals("student"))) {
                                nextEvent = reader.nextEvent();
                            }
                            reader.nextEvent();
                        } else {
                            writer.add(nextEvent);
                        }
                    } else {
                        writer.add(nextEvent);
                    }
                }
            }
            is.close();
            reader.close();
            os.close();
            writer.close();
            try {
                Files.move(Path.of(temp.toURI()), Path.of(file.toURI()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println("Ошибка при заменен файла");
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Файл не найден");
        } catch (XMLStreamException xmlStreamException) {
            System.err.println("Проблема чтения/записи в XML файл");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (reader != null)
                    reader.close();
                if (os != null)
                    os.close();
                if (writer != null)
                    writer.close();
            } catch (XMLStreamException e) {
                System.err.println("Ошибка при закрытии потока записи в XML");
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии потока записи");
            }
        }
        return false;
    }
}


