package com.tablemasterbackend.util.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;

public class Password {
    public Password() {
    }

    public String encode(CharSequence rawPassword) {
        int hashStrength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(hashStrength, new SecureRandom());

        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public boolean matches(String encodedPassword, String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
