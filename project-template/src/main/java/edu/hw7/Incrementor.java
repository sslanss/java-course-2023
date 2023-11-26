package edu.hw7;

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

    //создать класс с потоками?
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        Incrementor incrementor = new Incrementor(0);
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(incrementor::inc);
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(incrementor.get());
    }
}


