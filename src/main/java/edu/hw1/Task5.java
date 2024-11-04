package edu.hw1;

@SuppressWarnings("ParameterAssignment")
public final class Task5 {

    private static final int NUMBER_BASE = 10;

    private Task5() {
    }

    private static boolean isPalindrome(int number) {
        int originalNumber = number;
        int reversedNumber = 0;
        while (number > 0) {
            reversedNumber = reversedNumber * NUMBER_BASE + number % NUMBER_BASE;
            number /= NUMBER_BASE;
        }
        return reversedNumber == originalNumber;
    }

    private static boolean isNumberSingleDigit(int number) {
        return number / NUMBER_BASE == 0;
    }

    private static boolean isNumberOfDigitsOdd(int number) {
        String numberString = String.valueOf(number);
        int numberOfDigits = numberString.length();
        return numberOfDigits % 2 > 0;
    }

    public static boolean isPalindromeDescendant(int number) {
        if (isNumberSingleDigit(number)) {
            return false;
        } else if (isPalindrome(number)) {
            return true;
        } else {
            if (isNumberOfDigitsOdd(number)) {
                return false;
            }
            int counter = 0;
            int newNumber = 0;
            int digit;
            while (number > 0) {
                digit = (number % (NUMBER_BASE * NUMBER_BASE) / NUMBER_BASE + number % NUMBER_BASE);
                newNumber = newNumber + digit * (int) (Math.pow(NUMBER_BASE, counter));
                counter = digit / NUMBER_BASE == 0 ? counter + 1 : counter + 2;
                number /= (NUMBER_BASE * NUMBER_BASE);
            }
            return isPalindromeDescendant(newNumber);
        }
    }
}
