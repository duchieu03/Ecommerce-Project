package com.ecommerceproject.ecommerceproject.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encryptPassword(String password){

        return passwordEncoder.encode(password);
    }

    public static boolean verifyPassword(String password, String hash){
        return passwordEncoder.matches(password,hash);
    }
}
