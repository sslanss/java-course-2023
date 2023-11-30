package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Incrementor {

    private final AtomicInteger counter;

    public Incrementor(int initialValue) {
        counter = new AtomicInteger(initialValue);
    }

    public void inc() {
        counter.incrementAndGet();
    }

    public long get() {
        return counter.get();
    }
}


