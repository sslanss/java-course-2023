package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Factorial {
    private final long value;

    public Factorial(long value) {
        this.value = value;
    }

    public BigInteger countParallel() {
        if (value <= 0) {
            return BigInteger.valueOf(value);
        }
        return LongStream.rangeClosed(1, value)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static void main(String[] args) {
        Factorial factorial = new Factorial(10);
        System.out.println(factorial.countParallel());
    }
}
