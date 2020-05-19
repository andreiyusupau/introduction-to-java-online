package com.nevermind.archive.server.dao;

import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.client.model.Record;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLArchiveDAO implements ArchiveDAO {

    private final String fileName = "archive.xml";

    public XMLArchiveDAO() {
    }

    @Override
    public boolean create(Record record) {
        boolean flag = false;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                flag = true;
            } catch (IOException ioe) {
                System.err.println("Не удалось создать новый файл");
                return false;
            }
        }
        XMLEventReader reader=null;
        XMLEventWriter writer=null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
            writer = xmlOutputFactory.createXMLEventWriter(new FileOutputStream(file));
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
                    if (nextEvent.isAttribute()) {
                        currId = Long.parseLong(nextEvent.asCharacters().getData());
                    }
                    if (nextEvent.isEndElement()) {
                        EndElement endElement = nextEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("students")) {
                            addRecord(writer, eventFactory, record, currId);
                            Characters space;
                            space = eventFactory.createSpace("\n");
                            writer.add(space);
                            writer.add(endElement);
                        } else {
                            writer.add(nextEvent);
                        }
                    } else {
                        writer.add(nextEvent);
                    }
                }
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Файл не найден");
        } catch (XMLStreamException xmlStreamException) {
            System.err.println("Проблема чтения/записи в XML файл");
        }finally {
            try {
                if(reader!=null)
                reader.close();
                if(writer!=null)
                writer.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void addRecord(XMLEventWriter writer, XMLEventFactory eventFactory, Record record, long currId) throws XMLStreamException {
        StartElement startElement;
        startElement = eventFactory.createStartElement(new QName("student"), null, null);
        writer.add(startElement);
        Attribute attribute;
        attribute = eventFactory.createAttribute("id", String.valueOf(currId + 1));
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

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                Record record=null;
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("student") &&
                            Long.parseLong(startElement.getAttributeByName(new QName("id")).getValue()) == id) {

                        record = new Record();
                       readRecord(reader,nextEvent,record);
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
            fileNotFoundException.printStackTrace();
        } catch (XMLStreamException xmlStreamException) {
            xmlStreamException.printStackTrace();
        }
        return null;
    }

    public void readRecord(XMLEventReader reader,XMLEvent nextEvent,Record record) throws XMLStreamException {
        record.setId(Long.parseLong(nextEvent.asStartElement().getAttributeByName(new QName("id")).getValue()));
        nextEvent = reader.nextEvent();
        record.setFirstName(nextEvent.asCharacters().getData());
        nextEvent = reader.nextEvent();
        record.setMiddleName(nextEvent.asCharacters().getData());
        nextEvent = reader.nextEvent();
        record.setLastName(nextEvent.asCharacters().getData());

        nextEvent = reader.nextEvent();
        record.setDateOfBirth(LocalDate.parse(nextEvent.asCharacters().getData()));

        nextEvent = reader.nextEvent();
        record.setYearOfEntry(Integer.parseInt(nextEvent.asCharacters().getData()));

        nextEvent = reader.nextEvent();
        record.setYearOfGraduation(Integer.parseInt(nextEvent.asCharacters().getData()));

        nextEvent = reader.nextEvent();
        record.setDescription(nextEvent.asCharacters().getData());
    }


    @Override
    public List<Record> readAll() {

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            List<Record> records = new ArrayList<>();
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                Record record = null;
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if(startElement.getName().equals("student")){
                            record = new Record();
                        readRecord(reader,nextEvent,record);
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
            e.printStackTrace();
            return null;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(Record record) {
        long targetId=record.getId();
        boolean flag = false;
        File file = new File(fileName);
        if (!file.exists()) {
            try  {
                file.createNewFile();
              flag=true;
            } catch (IOException ioe) {
                System.err.println("Не удалось создать новый файл");
                return false;
            }
        }
        XMLEventReader reader=null;
        XMLEventWriter writer=null;
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
            writer = xmlOutputFactory.createXMLEventWriter(new FileOutputStream(file));
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
                    if (nextEvent.isAttribute()) {
                        currId = Long.parseLong(nextEvent.asCharacters().getData());
                    }
                    if (nextEvent.isEndElement()) {
                        EndElement endElement = nextEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("students")) {
                            addRecord(writer, eventFactory, record, currId);
                            Characters space;
                            space = eventFactory.createSpace("\n");
                            writer.add(space);
                            writer.add(endElement);
                        } else {
                            writer.add(nextEvent);
                        }
                    } else {
                        writer.add(nextEvent);
                    }
                }
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Файл не найден");
        } catch (XMLStreamException xmlStreamException) {
            System.err.println("Проблема чтения/записи в XML файл");
        }finally {
            try {
                if(reader!=null)
                    reader.close();
                if(writer!=null)
                    writer.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    }


