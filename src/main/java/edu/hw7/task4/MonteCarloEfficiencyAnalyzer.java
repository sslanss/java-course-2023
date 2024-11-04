package edu.hw7.task4;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MonteCarloEfficiencyAnalyzer {

    private final static int NUMBER_OF_THREADS = 12;
    private final static int NUMBER_OF_SIMULATIONS = 100;
    private final static int NUMBER_OF_ITERATIONS = 1000000;
    private final static int NUMBER_OF_NANOSECONDS_IN_MILLISECOND = 1000000;
    private static final Logger LOGGER = LogManager.getLogger();

    public MonteCarloEfficiencyAnalyzer() {

    }

    public void countAverageExecutionTimeInMillis(List<Integer> numberOfThreads) {
        for (var number : numberOfThreads) {
            LOGGER.info("Number of threads: " + number + "\tAverage execution time: " + countAverageTime(number));
        }
    }

    public void countComputationErrors(List<Long> numberOfIterations) {
        for (var number : numberOfIterations) {
            LOGGER.info("Number of iterations: " + number + "\tcomputational error: "
                + countPIComputationError(number));
        }
    }

    private double countAverageTime(int numberOfThreads) {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++) {
            MonteCarlo.countPIWithConcurrency(numberOfThreads, NUMBER_OF_ITERATIONS);
        }
        return (double) (System.nanoTime() - startTime) / NUMBER_OF_SIMULATIONS / NUMBER_OF_NANOSECONDS_IN_MILLISECOND;
    }

    private double countPIComputationError(long numberOfIterations) {
        return Math.abs(MonteCarlo.countPIWithConcurrency(NUMBER_OF_THREADS, numberOfIterations) - Math.PI);
    }
}
