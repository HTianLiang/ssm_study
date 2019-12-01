package com.tl.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static String encoderPassword(String password){
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String str = "123456";
        String password = encoderPassword(str);
        System.out.println(password);

    }

}
