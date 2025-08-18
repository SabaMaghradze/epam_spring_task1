package com.example.gym.service;

import java.security.SecureRandom;

public final class UsernamePasswordUtil {

    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RND = new SecureRandom();

    public static String randomPassword10() {

        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            sb.append(ALPHANUM.charAt(RND.nextInt(ALPHANUM.length())));
        }

        return sb.toString();
    }

    public static String baseUsername(String first, String last) {
        return String.format("%s.%s",
                first.trim().replaceAll("\\s+", ""),
                last.trim().replaceAll("\\s+", ""));
    }

}
