package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    private Task3() {
    }

    private static boolean isValidInput(int[] first, int[] second) {
        return first != null && second != null && first.length > 0 && second.length > 0;
    }

    public static boolean isNestable(int[] first, int[] second) {
        if (!isValidInput(first, second)) {
            return false;
        } else {
            int firstMin = Arrays.stream(first).min().getAsInt();
            int firstMax = Arrays.stream(first).max().getAsInt();
            int secondMin = Arrays.stream(second).min().getAsInt();
            int secondMax = Arrays.stream(second).max().getAsInt();
            return firstMin > secondMin && firstMax < secondMax;
        }
    }
}
