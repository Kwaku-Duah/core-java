package com.threads.concurrency.Util;

public class SynchronizedMethodExample {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public static void main(String[] args) {
        SynchronizedMethodExample example = new SynchronizedMethodExample();

        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        Runnable decrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                example.decrement();
            }
        };

        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(decrementTask);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + example.count);
    }
}
