package edu.hw1;

public final class Task1 {
    private static final int SECONDS_IN_A_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String string) {
        if (!isStringValid(string)) {
            return -1;
        } else {
            String[] parts = string.split(":");
            if (isInvalidParts(parts)) {
                return -1;
            }
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            if (isInvalidSeconds(seconds)) {
                return -1;
            }
            return minutes * SECONDS_IN_A_MINUTE + seconds;
        }
    }

    private static boolean isStringValid(String string) {
        return string != null && !string.isEmpty();
    }

    private static boolean isInvalidParts(String[] parts) {
        return parts.length == 1;
    }

    private static boolean isInvalidSeconds(int seconds) {
        return seconds >= SECONDS_IN_A_MINUTE;
    }
}
