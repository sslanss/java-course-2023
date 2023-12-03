package edu.hw8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] workers;
    private final BlockingQueue<Runnable> taskQueue;
    private volatile AtomicInteger currentPoolSize;

    private final AtomicInteger activeThreadsSize;
    //наверное лучше volatile
    private volatile boolean isRunning;

    private final int maxPoolSize;

    private FixedThreadPool(int size) {
        currentPoolSize = new AtomicInteger(0);
        activeThreadsSize = new AtomicInteger(0);
        maxPoolSize = size;
        workers = new Thread[maxPoolSize];
        taskQueue = new LinkedBlockingQueue<>(maxPoolSize * maxPoolSize);
    }

    public static FixedThreadPool create(int size) {
        return new FixedThreadPool(size);
    }

    private void addWorker(Runnable firstTask) {
        currentPoolSize.incrementAndGet();
        workers[currentPoolSize.get() - 1] = new Thread(() -> {
            //не уверена...
            while (/*!Thread.interrupted() && */isRunning/* && */) {
                Runnable task = firstTask;
                while (task != null) {
                    activeThreadsSize.incrementAndGet();
                    task.run();
                    activeThreadsSize.decrementAndGet();
                    task = taskQueue.poll();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        //workers[currentPoolSize.get()-1].start();
    }

    @Override
    public void start() {
        isRunning = true;
        int size = currentPoolSize.get();
        for (int i = 0; i < size; i++) {
            workers[i].start();
        }
    }

    @Override
    public void execute(Runnable task) throws InterruptedException {
        if (task == null) {
            throw new NullPointerException();
        }
        int activeThreads = activeThreadsSize.get();
        if (activeThreads < currentPoolSize.get()) {
            /*while (!taskQueue.offer(task, 100, TimeUnit.MILLISECONDS)){
            }
            return;*/
            taskQueue.add(task);
            return;//?????????????
        }
        if (activeThreads == currentPoolSize.get()) {
            if (maxPoolSize > currentPoolSize.get()) {
                addWorker(task);
                return;
            }
            while (!taskQueue.offer(task, 100, TimeUnit.MILLISECONDS)) {
            }
        }
    }

    @Override
    public void close() throws Exception {
        isRunning = false;

        int size = currentPoolSize.get();
        for (int i = 0; i < size; i++) {
            workers[i].join();
        }
        System.out.println(taskQueue.size());
        taskQueue.clear();

    }
}
