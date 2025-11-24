package com.urlshortening.backend;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;

public class KeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        System.out.println(Encoders.BASE64.encode(key.getEncoded()));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin123"));
        System.out.println(encoder.matches("admin123", "$2a$10$AmLlvJx/NlzSHckWWACBG.DMQxWa1FgPlo7JGaTd09uQmg8tOc9xK"));
    }
}
