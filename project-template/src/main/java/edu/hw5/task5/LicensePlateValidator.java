package edu.hw5.task5;

import java.util.regex.Pattern;

public class LicensePlateValidator {

    private LicensePlateValidator() {

    }

    private static final Pattern LICENSE_PLATE_PATTERN =
        Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    public static boolean isLicenseValidValid(String str) {
        return (LICENSE_PLATE_PATTERN.matcher(str).find());
    }
}
