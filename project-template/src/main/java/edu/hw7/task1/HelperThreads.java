package edu.hw7.task1;

import java.util.ArrayList;

public class HelperThreads {

    public static void incByThreads(Incrementor incrementor, int numberOfThreads, int incTimes)
        throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add((new Thread(() -> {
                for (int j = 0; j < incTimes; j++) {
                    incrementor.inc();
                }
            })));
        }
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
    }
}
