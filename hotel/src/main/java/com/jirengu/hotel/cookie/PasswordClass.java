package com.jirengu.hotel.cookie;

import java.util.HashMap;

public class PasswordClass {
    public static HashMap<String, String> passwordMap = new HashMap<>();

    static {
        passwordMap.put("mirai", "327");
        passwordMap.put("skygrass", "531");
        passwordMap.put("nekoi", "12345");
    }

    public static void registerUser(String username, String password) {
        passwordMap.put(username, password);
    }

    public static boolean verify(String username, String password) {
        String p = passwordMap.get(username);
        if(p == null) {
            return false;
        }
        return p.equals(password);
    }
}
