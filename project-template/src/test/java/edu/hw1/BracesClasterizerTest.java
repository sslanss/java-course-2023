package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BracesClasterizerTest {
    @Test
    public void functionShouldReturnCorrectResultHandlingPositiveNumber() {
        int inputNumber = 55555;
        int result = Task2.countDigits(inputNumber);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void functionShouldReturnCorrectResultHandlingZero() {
        int inputNumber = 0;
        int result = Task2.countDigits(inputNumber);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void functionShouldReturnCorrectResultHandlingNegativeNumber() {
        int inputNumber = -5;
        int result = Task2.countDigits(inputNumber);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void functionShouldReturnCorrectResultHandlingMinInt() {
        int inputNumber = Integer.MIN_VALUE;
        int result = Task2.countDigits(inputNumber);
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    public void functionShouldReturnCorrectResultHandlingMaxInt() {
        int inputNumber = Integer.MAX_VALUE;
        int result = Task2.countDigits(inputNumber);
        Assertions.assertThat(result).isEqualTo(10);
    }
}
