package com.nevermind.archive.common.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;

public class Util {
    //функция для ввода n с клавиатуры
    public static int readN(String question, int min, int max) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное число
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод redLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print(question);

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной n
                n = Integer.parseInt(br.readLine());

                //Если введённое n не является положительным числом, выводим ошибку, цикл повторяется
                if (n < min || n > max) {
                    System.err.println("Значение должно лежать в пределах от " + min + " до " + max);
                }
                //Если введённое n является подходящим, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");
            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return n;
    }

    //функция для ввода s с клавиатуры
    public static String readS(String question) {

        //Инициализируем объект класса BufferedReader для считывания ввода с клавиатуры

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;

        //Применение while(true) и break позволит повторять операцию ввода пока не будет введено правильное значение
        while (true) {

            /*Так как метод parseInt выбрасывает исключение NumberFormatException, а метод readLine - исключение IOException,
            применяем конструкцию try-catch*/

            try {
                System.out.print(question);

                //считываем ввод с клавиатуры и пытаемся присваивоить его переменной s
                s = String.valueOf(br.readLine());

                //Если введённое s пустое повторяем цикл
                if (s.equals("")) {
                    System.err.println("Введите хоть что-нибудь.");
                }
                //Если введённое s является подходящим, выходим из цикла
                else {
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Неправильный формат данных.");

            } catch (IOException ioe) {
                System.err.println("Проблема при вводе данных.");
            }
        }
        return s;

    }
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

}
