package edu.hw7.task1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IncrementorTest {

    @RepeatedTest(10)
    public void incrementorShouldReturnsSameResults() throws InterruptedException {
        Incrementor incrementor = new Incrementor(0);
        HelperThreads.incByThreads(incrementor, 10, 10);
        Assertions.assertThat(incrementor.get()).isEqualTo(100);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 10, 20, 100})
    public void incrementorShouldWorksForManyThreadsAmount(int numberOfThreads) throws InterruptedException {
        Incrementor incrementor = new Incrementor(0);
        HelperThreads.incByThreads(incrementor, numberOfThreads, 10);
        Assertions.assertThat(incrementor.get()).isEqualTo(numberOfThreads * 10L);
    }
}
