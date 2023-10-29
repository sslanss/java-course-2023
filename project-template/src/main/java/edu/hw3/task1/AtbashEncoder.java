package edu.hw3.task1;

import java.util.ArrayList;
import java.util.List;

public final class AtbashEncoder {

    private static final int ALPHABET_SIZE = 26;

    private static final List<Character> UPPER_CASE_ALPHABET = new ArrayList<Character>() {
        {
            for (char c = 'A'; c <= 'Z'; c++) {
                add(c);
            }
        }
    };

    private static final List<Character> LOWER_CASE_ALPHABET = new ArrayList<Character>() {
        {
            for (char c = 'a'; c <= 'z'; c++) {
                add(c);
            }
        }
    };

    private AtbashEncoder() {
    }

    private static char encryptUpperCaseLetter(char letter) {
        return UPPER_CASE_ALPHABET.get(ALPHABET_SIZE - (letter - 'A') - 1);
    }

    private static char encryptLowerCaseLetter(char letter) {
        return LOWER_CASE_ALPHABET.get(ALPHABET_SIZE - (letter - 'a') - 1);
    }

    private static boolean isLatin(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static String atbash(String string) {
        StringBuilder encrypted = new StringBuilder();
        if (string != null) {
            for (int i = 0; i < string.length(); i++) {
                char currentLetter = string.charAt(i);
                if (isLatin(currentLetter)) {
                    if (Character.isLowerCase(currentLetter)) {
                        currentLetter = encryptLowerCaseLetter(currentLetter);
                    } else {
                        currentLetter = encryptUpperCaseLetter(currentLetter);
                    }
                    encrypted.append(currentLetter);
                } else {
                    encrypted.append(currentLetter);
                }
            }
        }
        return encrypted.toString();
    }
}
