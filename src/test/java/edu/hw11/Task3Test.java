package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task3Test {

    private long countFibonacci(int number) {
        if (number <= 1) {
            return number;
        } else {
            return countFibonacci(number - 1) + countFibonacci(number - 2);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    public void countFibonacciShouldReturnCorrectValue(int value)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> fibonacciClass = FibonacciClassProvider.getFibonacciClass();
        Method method = fibonacciClass.getMethod("count", int.class);
        long result = (long) method.invoke(fibonacciClass.getConstructor().newInstance(), value);
        Assertions.assertThat(result)
            .isEqualTo(countFibonacci(value));
    }
}
