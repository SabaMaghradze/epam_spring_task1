package com.example.gym.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static <T> String generateUniqueUsername(String first, String last,
                                                    Supplier<List<T>> findByName) {
        String base = baseUsername(first, last);
        List<T> same = findByName.get();
        if (same.isEmpty()) return base;
        return base + (same.size() + 1);
    }

}
