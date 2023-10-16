package edu.hw1;

import java.util.Arrays;

public final class Task6 {

    private static final int K = 6174;

    private static final int NUMBER_BASE = 10;

    private static final int SECOND_DEGREE_NUMBER_BASE = 100;

    private static final int THIRD_DEGREE_NUMBER_BASE = 1000;

    private static final int MIN_FOUR_DIGITS_NUMBER = 1000;

    private static final int MAX_FOUR_DIGITS_NUMBER = 9999;

    private Task6() {
    }

    private static boolean isNumberFourDigit(int number) {
        return number > MIN_FOUR_DIGITS_NUMBER && number < MAX_FOUR_DIGITS_NUMBER;

    }

    private static boolean isNumberDigitsAreTheSame(int number) {
        int firstDigit = number / THIRD_DEGREE_NUMBER_BASE;
        int secondDigit = number / SECOND_DEGREE_NUMBER_BASE % NUMBER_BASE;
        int thirdDigit = number / NUMBER_BASE % NUMBER_BASE;
        int fourthDigit = number % NUMBER_BASE;
        return firstDigit == secondDigit && secondDigit == thirdDigit && thirdDigit == fourthDigit;
    }

    private static int[] convertNumberToDigitArray(int number) {
        final int NUMBER_LENGTH = 4;
        int[] digitsArray = new int[NUMBER_LENGTH];
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
