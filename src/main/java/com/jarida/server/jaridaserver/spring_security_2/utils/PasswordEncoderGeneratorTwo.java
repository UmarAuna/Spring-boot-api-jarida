package com.jarida.server.jaridaserver.spring_security_2.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class PasswordEncoderGeneratorTwo {
    public static void main(String[] args) {
        PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(PasswordEncoder.encode("admin"));
    }

    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
