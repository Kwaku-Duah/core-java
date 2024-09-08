/**
 * The `ThreadPoolExample` class demonstrates how to create a fixed thread pool and submit tasks to it
 * using Java's ExecutorService.
 */
package com.threads.concurrency.Util;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private int taskId;

    public Task(int id) {
        this.taskId = id;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is running in " + Thread.currentThread().getName());
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a thread pool of 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to thread pool
        for (int i = 1; i <= 10; i++) {
            executorService.submit(new Task(i));
        }

        // Shutdown the thread pool
        executorService.shutdown();
    }
}
