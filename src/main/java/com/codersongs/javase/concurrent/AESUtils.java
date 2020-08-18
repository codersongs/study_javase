package com.codersongs.javase.concurrent;


import sun.security.krb5.internal.crypto.Aes128;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESUtils {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/NoPadding";
    private static byte ivBytes[] = "0000000000000000".getBytes();
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    private String secretKey = "9626248542f7cDA51f812dC6dD3d92A6";

    public String decrypt(String content) {
        try {
            byte[] result = decryptCipher.doFinal(ByteFormat.hexToBytes(content.trim()));
            return new String(result, "utf-8").trim();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public String encrypt(String content) {
        try {
            byte[] byteContent = padString(content).getBytes(StandardCharsets.UTF_8);
            byte[] result = encryptCipher.doFinal(byteContent);
            return ByteFormat.toHexLower(result).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String padString(String source) {
        char paddingChar = ' ';
        int size = 16;
        int x = 0;
        x = source.getBytes(StandardCharsets.UTF_8).length % size;
        int padLength = size - x;
        StringBuilder sb = new StringBuilder(source);
        for (int i = 0; i < padLength; i++) {
            sb.append(paddingChar);
        }
        return sb.toString();
    }

    public AESUtils() {
        try {
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), KEY_ALGORITHM);
            Cipher encryptCipherObj = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            encryptCipherObj.init(Cipher.ENCRYPT_MODE, key, iv);
            encryptCipher = encryptCipherObj;
            Cipher decryptCipherObj = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            decryptCipherObj.init(Cipher.DECRYPT_MODE, key, iv);
            decryptCipher = decryptCipherObj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AESUtils aesUtils = new AESUtils();
        String abcd = aesUtils.encrypt("abcd");
        System.out.println(abcd);
    }
}