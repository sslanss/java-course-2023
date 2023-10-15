package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String string) {
        if (string != null) {
            StringBuilder builder = new StringBuilder();
            int i;
            for (i = 0; i < string.length() - 1; i += 2) {
                builder.append(string.charAt(i + 1));
                builder.append(string.charAt(i));
            }
            return builder.toString() + (i == string.length() - 1 ? string.charAt(i) : "");
        }
        return null;
    }
}
