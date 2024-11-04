package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    public void functionShouldReturnFalseHandlingSingleDigitNumber() {
        int inputValue = 0;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueHandlingPalindromeOddDigitsNumber() {
        int inputValue = 4334;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnFalseHandlingNotAPalindromeNumber() {
        int inputValue = 433411;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueHandlingPalindromeEvenDigitsNumber() {
        int inputValue = 43534;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnFalseHandlingNegativeNumber() {
        int inputValue = -43534;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    public void functionShouldReturnTrueHandlingPalindromeDescendantNumber() {
        int inputValue = 13001120;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnTrueHandlingPalindromeDescendantWithAdjacentDigitsSumMoreThanNine() {
        int inputValue = 565656;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    public void functionShouldReturnFalseHandlingNotAPalindromeDescendantWithAdjacentDigitsSumMoreThanNine() {
        int inputValue = 665667;
        boolean result = Task5.isPalindromeDescendant(inputValue);
        Assertions.assertThat(result).isEqualTo(false);
    }
}
