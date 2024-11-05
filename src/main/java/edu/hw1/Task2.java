package edu.hw1;

public final class Task2 {

    private static final int NUMBER_BASE = 10;

    private Task2() {
    }

    @SuppressWarnings("ParameterAssignment")
    public static int countDigits(int number) {
        int digitsAmount = 1;
        number /= NUMBER_BASE;
        number = Math.abs(number);
        while (number > 0) {
            number /= NUMBER_BASE;
            digitsAmount++;
        }
        return digitsAmount;
    }
}
