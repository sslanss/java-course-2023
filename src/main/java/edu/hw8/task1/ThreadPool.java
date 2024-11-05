package edu.hw8.task1;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable) throws InterruptedException;
}
