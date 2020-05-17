package com.nevermind.archive.server.dao;

import com.nevermind.archive.client.model.User;
import com.nevermind.archive.client.util.ClientUtil;
import com.nevermind.archive.common.dao.UserDAO;

import java.io.*;

public class XMLUserDAO implements UserDAO {

    private String fileName = "users.xml";

    public XMLUserDAO() {
    }

    @Override
    public boolean create(User user) {
        File file = new File(fileName);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                file.createNewFile();
                bw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            } catch (IOException ioe) {
                System.err.println("Не удалось создать новый файл");
                return false;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {


            bw.newLine();
            bw.write(user.toString());
            return true;
        } catch (IOException io) {
            System.err.println("Ошибка при записи в файл");
            return false;
        }
    }

    @Override
    public boolean login(String email, String password) {
        File file = new File(fileName);
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String currLine;
            String[] credentials;
            br.readLine();
            while ((currLine = br.readLine()) != null) {
                credentials = currLine.trim().split("/");

                if (credentials[0].equals(email) && ClientUtil.validatePassword(password, credentials[1])) {
                    System.out.println("LOGIN SUCCESS ");
                    return true;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Файл не найден");
        } catch (IOException io) {
            System.err.println("Проблема чтения из файла");
        }
        return false;
    }

public boolean checkUserRights(String email, String password){
    File file = new File(fileName);
    try (BufferedReader br
                 = new BufferedReader(new FileReader(file))) {

        String currLine;
        String[] credentials;
        br.readLine();
        while ((currLine = br.readLine()) != null) {
            credentials = currLine.trim().split("/");

            if (credentials[0].equals(email) && ClientUtil.validatePassword(password, credentials[1])&&credentials[2].equals("true")) {
                return true;
            }
        }
    } catch (FileNotFoundException fnfe) {
        System.err.println("Файл не найден");
    } catch (IOException io) {
        System.err.println("Проблема чтения из файла");
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
