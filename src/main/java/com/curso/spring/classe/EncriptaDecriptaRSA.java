package com.curso.spring.classe;

import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;

public class EncriptaDecriptaRSA {

    public static final String ALGORITHM = "RSA";

    @Autowired
    private PublicKey publicKey;

    @Autowired
    private PrivateKey privateKey;

    public static KeyPair genKeyPair() throws Exception {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(512);
        KeyPair keyPair = generator.generateKeyPair();

        return keyPair;
    }

    public String encrypt(String string) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return new String(cipher.doFinal(string.getBytes()));
    }

    public String decrypt(String string) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(string.getBytes()));
    }
}
