package com.nevermind.library.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Pattern;

public class UserUtil {


    //https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
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
