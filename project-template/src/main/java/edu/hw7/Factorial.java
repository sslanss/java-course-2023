package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Factorial {
    private long value;
    public Factorial(long value){
        this.value = value;
    }

    public BigInteger countParallel(){
        return LongStream.rangeClosed(1, value)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static void main(String []args){
        Factorial factorial = new Factorial(10);
        System.out.println(factorial.countParallel());
    }
}
