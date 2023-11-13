package edu.hw5.task4;

import java.util.regex.Pattern;

public class PasswordValidator {

    private PasswordValidator() {

    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[~!@#$%^&*|\\^]");

    public static boolean isPasswordValid(String str) {
        return (PASSWORD_PATTERN.matcher(str).find());
    }
}
