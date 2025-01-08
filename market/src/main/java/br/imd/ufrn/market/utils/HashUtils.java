package br.imd.ufrn.market.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class HashUtils {
    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static Integer decodeBase64ToInt(String input) {
        String str = new String(Base64.getDecoder().decode(input));
        return Integer.parseInt(str);
    }
}