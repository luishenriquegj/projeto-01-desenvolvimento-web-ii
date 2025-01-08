package br.imd.ufrn.market.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseUtils {
    public static Map<String, String> makeMessage(String message) {
        Map<String, String> finalMessage = new HashMap<>();
        finalMessage.put("response", message);

        return finalMessage;
    }
    public static Map<String, String> makeMessageWithCode(String message, String code) {
        Map<String, String> finalMessage = makeMessage(message);
        finalMessage.put("code", code);

        return finalMessage;
    }
    public static Map<String, String> makeMessageWithToken(String message, String token) {
        Map<String, String> finalMessage = makeMessage(message);
        finalMessage.put("token", token);

        return finalMessage;
    }
    public static <T> Map<String, T> makeMessageWithObject(T object) {
        Map<String, T> finalMessage = new HashMap<>();
        finalMessage.put("response", object);

        return finalMessage;
    }
    public static <T> Map<String, List<T>> makeMessageWithList(List<T> list){
        Map<String, List<T>> finalMessage = new HashMap<>();
        finalMessage.put("response", list);

        return finalMessage;
    }
}