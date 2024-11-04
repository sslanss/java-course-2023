package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringChecker {

    private SubstringChecker() {

    }

    public static boolean isSubstring(String string, String other) {
        if (other.isEmpty()) {
            return false;
        }
        Pattern substring = Pattern.compile(other);
        Matcher matcher = substring.matcher(string);
        return matcher.find();
    }
}
