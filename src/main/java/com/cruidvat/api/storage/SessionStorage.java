package com.cruidvat.api.storage;

import java.util.HashMap;
import java.util.Map;

public class SessionStorage {

    private static Map<String, String> sessionStorage = new HashMap<>();

    public static void add(String key, String value) {
        sessionStorage.put(key, value);
    }

    public static String get(String key) {
        return sessionStorage.get(key);
    }

    private SessionStorage() {

    }
}
