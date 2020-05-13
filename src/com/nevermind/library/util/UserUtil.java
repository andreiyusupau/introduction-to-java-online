package com.nevermind.library.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class UserUtil {

    public static byte[] hashPass(String pass) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        }catch (NoSuchAlgorithmException nsae){
            System.err.println("Заданный алгоритм шифрования не существует");
        }catch (InvalidKeySpecException ikse){
            System.err.println("Неверные входные параметры шифрования");
        }
        return null;
    }
}
