package com.nevermind.library.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.regex.Pattern;

public class UserUtil {

    //метод для хеширования пароля
    public static String generatePasswordHash(String password) {

        int iterations;
        iterations = 1000;//количество операций хеширования

        char[] chars;
        chars = password.toCharArray(); //переводим пароль в массив символов

        try {
            byte[] salt;
            salt = getSalt(); //создаем "соль"

            PBEKeySpec spec;
            spec = new PBEKeySpec(chars, salt, iterations, 64 * 8); //задаем параметры алгоритма хеширования

            SecretKeyFactory skf;
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); //выбираем алгоритм "PBKDF2WithHmacSHA1"

            byte[] hash;
            hash = skf.generateSecret(spec).getEncoded(); //создаем хеш пароля

            Base64.Encoder en = Base64.getEncoder();//создаем кодировщик
            //для валидации пароля в будущем нам потребуется знать число итераций и соль,
            // поэтому записываем их вместе с паролем в формате String с разделителем ":"
            return iterations + ":" + en.encodeToString(salt) + ":" + en.encodeToString(hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            System.err.println("Задан неверный алгоритм хеширования");
        }
        return null;
    }

    //метод для создания случайной "соли"
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");//создаем объект SecureRandom

        byte[] salt = new byte[16]; //создаем массив типа byte

        sr.nextBytes(salt); //генерируем случайное значение
        return salt;
    }

    //функция валидации пароля (originalPassword подается в исходном виде, stored - в хешированном (как он хранится в файле))
    public static boolean validatePassword(String originalPassword, String storedPassword) {
        String[] parts;
        parts = storedPassword.split(":"); //считываем "соль", число итерация и хешированный пароль из хранящегося пароля

        int iterations;
        iterations = Integer.parseInt(parts[0]); //присваиваем число итераций переменной iterations

        Base64.Decoder dec = Base64.getDecoder(); //раскодировщик, для перевода из строки в массив byte

        byte[] salt;
        salt = dec.decode(parts[1]); //указываем "соль"

        byte[] hash;
        hash = dec.decode(parts[2]); //указываем хеш пароля

        try {
            //создаем алгоритм хеширования с теми же параметрами, что были заданы при создании пароля
            PBEKeySpec spec;
            spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);

            SecretKeyFactory skf;
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  //выбираем алгоритм "PBKDF2WithHmacSHA1"

            byte[] testHash;
            testHash = skf.generateSecret(spec).getEncoded(); //хешируем введенный пароль

            return Arrays.equals(testHash, hash); //сравниваем его с тем, который хранится в базе

        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            System.err.println("Задан неверный алгоритм хеширования");
        }
        return false;
    }


    //функция валидации email
    public static boolean isEmailValid(String email) {
        //создаем регулярное выражение, отражающее правильную форму email
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        //компилируем паттерн
        Pattern pat;
        pat = Pattern.compile(emailRegex);

        //сравниваем введенный email с паттерном
        return email != null && pat.matcher(email).matches();
    }

    //функция для отправки уведомлений на email пользователям (с помощью пакета javax.mail)
    //Протестировал со своей почты - все работает.
    //для работы с gmail понадобилось открыть доступ небезопасным приложениям.
    // Для реального приложения потребуется почта библиотеки напоодобие library@gmail.com, которая будет отправлять все уведомления
    public static void sendMail(ArrayList<String> to, String subject, String content) {
        Properties props = new Properties(); //создаем хеш-таблицу свойств
        String host = "smtp.gmail.com"; //задаем сервер
        String from = "library@gmail.com"; //задаем почту, с которой будет происходить отправка
        String password = "password"; //пароль к почте
        //вносим параметры в хеш-таблицу
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        //создаем новую сессию с указанными параметрами
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        try {
            //создаем сообщение
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from)); //устанавливаем отправителя

            //создаем список получателей
            for (String recipient : to) {
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipient));
            }

            msg.setSubject(subject); //задаем тему
            msg.setText(content); //задаем содержимое
            Transport.send(msg); //пытаемся отправить
        } catch (AddressException e) {
            System.err.println("Неверный email адрес");
        } catch (MessagingException e) {
            System.err.println("Ошибка при отправке сообщения");
        }
    }

}
