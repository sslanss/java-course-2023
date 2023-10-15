package edu.hw1;

import java.util.Arrays;

public final class Task6 {

    private static final int K = 6174;

    private static final int NUMBER_BASE = 10;

    private Task6() {
    }

    private static boolean isNumberFourDigit(int number) {
        return number > (int) Math.pow(NUMBER_BASE, 3)
            && number < (int) Math.pow(NUMBER_BASE, 4);
    }

    private static boolean isNumberDigitsAreTheSame(int number) {
        int firstDigit = number / (int) Math.pow(NUMBER_BASE, 3);
        return (firstDigit == number / (int) Math.pow(NUMBER_BASE, 2) % NUMBER_BASE
            && firstDigit == number / NUMBER_BASE % NUMBER_BASE && firstDigit == number % NUMBER_BASE);
    }

    private static int[] convertNumberToDigitArray(int number) {
        final int NUMBER_LENGTH = 4;
        int[] digitsArray = new int[4];
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            digitsArray[i] = number % NUMBER_BASE;
            number /= NUMBER_BASE;
        }
        return digitsArray;
    }

    private static int countKRecursive(int number) {
        int result = 0;
        if (number == K) {
            return result;
        } else {
            int[] digitsArray = convertNumberToDigitArray(number);
            Arrays.sort(digitsArray);
            int minNumber = 0;
            int maxNumber = 0;
            for (int i = 0; i < digitsArray.length; i++) {
                minNumber += digitsArray[i] * (int) Math.pow(NUMBER_BASE, digitsArray.length - i - 1);
                maxNumber += digitsArray[digitsArray.length - i - 1] * (int) Math.pow(NUMBER_BASE,
                    digitsArray.length - i - 1);
            }
            return countKRecursive(maxNumber - minNumber) + 1;
        }
    }

    public static int countK(int number) {
        if (isNumberFourDigit(number) && !isNumberDigitsAreTheSame(number)) {
            return countKRecursive(number);
        } else return -1;
    }
}
