package edu.hw5.task4;

import java.util.regex.Pattern;

public class PasswordValidator {
    private static final Pattern passwordPattern = Pattern.compile("[~!@#$%^&*|\\^]");
    public static boolean isPasswordValid(String str) {
        return (passwordPattern.matcher(str).find());
    }
}
