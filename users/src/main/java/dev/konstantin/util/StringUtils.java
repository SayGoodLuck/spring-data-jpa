package dev.konstantin.util;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isStringContainOnlyAlphabetic(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isAlphabetic(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        return Pattern.matches("^(.+)@(.+)$", email);
    }
}
