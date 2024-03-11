package edu.hw7.task4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class MonteCarlo {

    private MonteCarlo() {
    }

    private final static double SECTORS_COUNT_IN_CIRCLE = 4.0;

    public static double countPI(long numberOfIterations) {
        int count = 0;
        for (int i = 0; i < numberOfIterations; i++) {
            double x = Math.random();
            double y = Math.random();
            if ((x * x) + (y * y) <= 1) {
                count++;
            }
        }
        return SECTORS_COUNT_IN_CIRCLE * ((double) count / numberOfIterations);
    }

    private static long countPIIteration(long iterationPerThread) {
        long localCounter = 0;
        for (int j = 0; j < iterationPerThread; j++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if (x * x + y * y <= 1) {
                localCounter++;
            }
        }
        return localCounter;
    }

    public static double countPIWithConcurrency(int numberOfThreads, long numberOfIterations) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)) {
            LongAdder totalCounter = new LongAdder();
            long iterationPerThread = Math.ceilDiv(numberOfIterations, numberOfThreads);
            CompletableFuture<?>[] futures = new CompletableFuture[numberOfThreads];

            for (int i = 0; i < numberOfThreads; i++) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    long localCounter = countPIIteration(iterationPerThread);
                    totalCounter.add(localCounter);
                }, executorService);
                futures[i] = future;
            }
            CompletableFuture.allOf(futures).join();
            executorService.shutdown();
            return SECTORS_COUNT_IN_CIRCLE * totalCounter.sum() / numberOfIterations;
        }
    }

}
