package com.nevermind.archive.server.dao;

import com.nevermind.archive.client.ArchiveClient;
import com.nevermind.archive.client.controller.RecordController;
import com.nevermind.archive.common.dao.ArchiveDAO;
import com.nevermind.archive.client.model.Record;

import java.io.*;
import java.util.List;

public class XMLArchiveDAO implements ArchiveDAO {

    private String fileName="archive.xml";

    public XMLArchiveDAO() {
    }

    @Override
    public boolean create(Record record) {
        File file = new File(fileName);
        if (!file.exists()) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                file.createNewFile();
                bw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            } catch (IOException ioe) {
                System.err.println("Не удалось создать новый файл");
                return false;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

bw.newLine();
            bw.write("<student>\n" +
                    "<id>" + record.getId() + "</id>\n" +
                    "<firstName>" + record.getFirstName() + "</firstName>\n" +
                    "<middleName>" + record.getMiddleName() + "</middleName>\n" +
                    "<lastName>" + record.getLastName() + "</lastName>\n" +
                    "<dateOfBirth>" + record.getDateOfBirth() + "</dateOfBirth>\n" +
                    "<status>" + record.getStatus() + " </status>\n" +
                    "<yearOfEntry>" + record.getYearOfEntry() + "</yearOfEntry>\n" +
                    "<yearOfGraduation>" + record.getYearOfGraduation() + "</yearOfGraduation>\n" +
                    "<description>" + record.getDescription() + "</description>\n" +
                    "</student>");

            return true;
        } catch (IOException io) {
            System.err.println("Ошибка при записи в файл");
            return false;
        }
    }

    @Override
    public Record read(long id) {
      /*  File file=new File(fileName);
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            int counter = 0;
            String currLine;
            while ((currLine = br.readLine()) != null && counter < bookId) {
                counter++;
            }
            String[] bookDetails;
            bookDetails = currLine.trim().split("/");
            return initBook(bookDetails);
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }*/
        return null;
    }

    @Override
    public List<Record> readAll() {
      /*  Message message;
        message = new Message("READ_ALL_RECORDS", null, rc.getUsername(), rc.getUserPassword());
        Message answer = null;
        try {
            answer = client.communicate(message);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла");
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден");
        }*/
        return null;
    }

    @Override
    public boolean update(Record record) {
        return false;
    }

}
