package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    public void functionShouldReturnMinusOneHandlingNotAPositiveFourDigitNumber() {
        int inputValue = -4334;
        int result = Task6.countK(inputValue);
        Assertions.assertThat(result).isEqualTo(-1);

        inputValue = 43345;
        result = Task6.countK(inputValue);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void functionShouldReturnOneHandlingConstKNumber() {
        int inputValue = 6174;
        int result = Task6.countK(inputValue);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void functionShouldReturnCorrectNumberHandlingFourDigitNumber() {
        int inputValue = 6621;
        int result = Task6.countK(inputValue);
        Assertions.assertThat(result).isEqualTo(5);
        inputValue = 6554;
        result = Task6.countK(inputValue);
        Assertions.assertThat(result).isEqualTo(4);
        inputValue = 1234;
        result = Task6.countK(inputValue);
        Assertions.assertThat(result).isEqualTo(3);
    }
}
