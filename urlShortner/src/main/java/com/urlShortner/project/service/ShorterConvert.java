package com.urlShortner.project.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import java.util.Random;
@Service
public class ShorterConvert {

    private static final  String BASE62="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int length=7;
    private static final Random random=new SecureRandom();

    public static String generate(){
        StringBuilder stringBuilder=new StringBuilder(length);

        for(int i=0;i<length;i++){
            stringBuilder.append(BASE62.charAt(random.nextInt(BASE62.length())));
        }
        return stringBuilder.toString();
    }
}
