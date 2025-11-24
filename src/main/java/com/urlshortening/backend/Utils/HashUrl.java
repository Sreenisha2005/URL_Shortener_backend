package com.urlshortening.backend.Utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashUrl {

    private String sha256(String input){
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            for (byte b : hash){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String toBase62(String hex){
        final String base62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        long decimal = Long.parseUnsignedLong(hex.substring(0, 12), 16);
        StringBuilder sb = new StringBuilder();
        while (decimal > 0){
            sb.append(base62.charAt((int)(decimal % 62)));
            decimal /= 62;
        }
        return sb.reverse().toString();
    }

    public String generate(String longurl){
        String hash = sha256(longurl);
        String base62 = toBase62(hash);
        return base62.substring(0, 7);
    }
}
