package edu.hw7.task2;

import java.math.BigInteger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialTest {
    @Test
    public void factorialShouldCountsCorrectlyWithNegativeNumbers() {
        Factorial factorial = new Factorial(-2);
        Assertions.assertThat(factorial.countParallel()).isEqualTo(-2);
    }

    @Test
    public void factorialShouldCountsCorrectly() {
        Factorial factorial = new Factorial(5);
        Assertions.assertThat(factorial.countParallel()).isEqualTo(120);
    }

    @Test
    public void factorialShouldCountsCorrectlyWithoutOverflowError() {
        Factorial factorial = new Factorial(20);
        Assertions.assertThat(factorial.countParallel()).isEqualTo(new BigInteger("2432902008176640000"));
    }
}
