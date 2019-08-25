package ru.bgbrakhi.carseller.utils;

import java.util.HashMap;
import java.util.Map;

public class Util {

    public static Map<String, String> postDataToMap(String data) {
        Map<String, String> result = new HashMap<>();
        String[] pairs = data.split("&");
        String[] kv;

        for (int i = 0; i < pairs.length; i++) {
            kv = pairs[i].split("=");
            result.put(kv[0], kv[1]);
        }
        return result;
    }
}
