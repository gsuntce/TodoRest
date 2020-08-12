package com.themonk.todo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        for(int i=1; i<=10; i++) {
            String encodedString = encoder.encode("dd");
            System.out.println(encodedString);
        }

    }
}
