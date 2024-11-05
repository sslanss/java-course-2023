package edu.hw7.task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class MonteCarloTest {
    private static final double EPS = 0.01;
    private static final long NUMBER_OF_ITERATIONS = 1000000;
    private static final int NUMBER_OF_THREADS = 12;

    @RepeatedTest(50)
    public void PIShouldCountCorrectly() {
        double pi = MonteCarlo.countPI(NUMBER_OF_ITERATIONS);
        Assertions.assertThat(pi).isCloseTo(Math.PI, within(EPS));
    }

    @RepeatedTest(50)
    public void PIShouldCountCorrectlyWithConcurrency() {
        Assertions.assertThat(MonteCarlo.countPIWithConcurrency(
            NUMBER_OF_THREADS,
            NUMBER_OF_ITERATIONS
        )).isCloseTo(Math.PI, within(EPS));
    }
}
