package edu.hw8.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private class Worker implements Runnable {
        @Override
        public void run() {
            while (isRunning()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private boolean isRunning() {
            return !Thread.currentThread().isInterrupted();
        }
    }

    private final int threadsNumber;
    private final Thread[] workers;
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean isRunning;

    private FixedThreadPool(int size) {
        threadsNumber = size;
        workers = new Thread[size];
        taskQueue = new LinkedBlockingQueue<>();
        isRunning = false;
    }

    public static FixedThreadPool create(int size) {
        return new FixedThreadPool(size);
    }

    @Override
    public void start() {
        isRunning = true;
        for (int i = 0; i < threadsNumber; i++) {
            workers[i] = new Thread(new Worker());
            workers[i].start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        if (isRunning) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void close() throws Exception {
        isRunning = false;
        for (int i = 0; i < threadsNumber; i++) {
            workers[i].join();
        }
        taskQueue.clear();
    }
}
