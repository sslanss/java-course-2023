package edu.hw5.task5;

import java.util.regex.Pattern;

public class LicensePlateValidator {
    private static final Pattern licensePlatePattern =
        Pattern.compile("^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}$");
    public static boolean isLicenseValidValid(String str) {
        return (licensePlatePattern.matcher(str).find());
    }
}
