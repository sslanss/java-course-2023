package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    private static boolean isValidInput(int[] first, int[] second) {
        return first != null && second != null && first.length > 0 && second.length > 0;
    }

    public static boolean isNestable(int[] first, int[] second) {
        if (isValidInput(first, second)) {
            int firstMin = first[0];
            int firstMax = first[0];
            for (int i = 1; i < first.length; i++) {
                if (firstMin > first[i]) {
                    firstMin = first[i];
                } else if (firstMax < first[i]) {
                    firstMax = first[i];
                }
            }
            int secondMin = second[0];
            int secondMax = second[0];
            for (int i = 1; i < second.length; i++) {
                if (secondMin > second[i]) {
                    secondMin = second[i];
                } else if (secondMax < second[i]) {
                    secondMax = second[i];
                }
            }
            return firstMin > secondMin && firstMax < secondMax;
        }
        return false;
    }
}
