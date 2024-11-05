package edu.hw3.task4;

import java.util.Comparator;
import java.util.TreeMap;

public final class ArabicToRomanConverter {

    private static final int MIN_ROMAN_VALUE = 1;

    private static final int MAX_ROMAN_VALUE = 3999;

    @SuppressWarnings("checkstyle:MagicNumber")

    private static final TreeMap<Integer, String> ARABIC_TO_ROMAN = new TreeMap<>() {
        {
            put(1, "I");
            put(4, "IV");
            put(5, "V");
            put(9, "IX");
            put(10, "X");
            put(40, "XL");
            put(50, "L");
            put(90, "XC");
            put(100, "C");
            put(400, "CD");
            put(500, "D");
            put(900, "CM");
            put(1000, "M");
        }
    };

    private final static TreeMap<Integer, String> DESC_SORTED_ARABIC_TO_ROMAN =
        new TreeMap<>(Comparator.reverseOrder());

    private ArabicToRomanConverter() {

    }

    private static boolean isLetterValid(int value) {
        return value >= MIN_ROMAN_VALUE && value <= MAX_ROMAN_VALUE;
    }

    public static String convertToRoman(int value) {
        StringBuilder romanImpl = new StringBuilder();
        if (isLetterValid(value)) {
            DESC_SORTED_ARABIC_TO_ROMAN.putAll(ARABIC_TO_ROMAN);
            for (var key : DESC_SORTED_ARABIC_TO_ROMAN.keySet()) {
                while (value >= key) {
                    romanImpl.append(DESC_SORTED_ARABIC_TO_ROMAN.get(key));
                    value -= key;
                }
            }
        }
        return romanImpl.toString();
    }
}
