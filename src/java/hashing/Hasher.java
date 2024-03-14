/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author PHT
 */
public class Hasher {
    public static String hash(String password) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        System.out.println(hash("1"));
        System.out.println(hash("2"));
        System.out.println(hash("12345678"));
        System.out.println(hash("1").length());
    }
}
