package com.nevermind.archive.client.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Pattern;

public class ClientUtil {
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

    public static String generatePasswordHash(String password) {
        int iterations;
        iterations = 1000;
        char[] chars;
        chars = password.toCharArray();
        try {
            byte[] salt;
            salt = getSalt();
            PBEKeySpec spec;
            spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            SecretKeyFactory skf;
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash;
            hash = skf.generateSecret(spec).getEncoded();

            return iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            System.err.println("Задан неверный алгоритм хеширования");
        }
        return null;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static boolean validatePassword(String originalPassword, String storedPassword) {
        String[] parts;
        parts = storedPassword.split(":");
        int iterations;
        iterations = Integer.parseInt(parts[0]);
        byte[] salt;
        salt = fromHex(parts[1]);
        byte[] hash;
        hash = fromHex(parts[2]);
        try {
            PBEKeySpec spec;
            spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf;
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash;
            testHash = skf.generateSecret(spec).getEncoded();

            int diff;
            diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            System.err.println("Задан неверный алгоритм хеширования");
        }
        return false;
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    //https://howtodoinjava.com/regex/java-regex-validate-email-address/
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pat;
        pat = Pattern.compile(emailRegex);

        return email != null && pat.matcher(email).matches();
    }


}
