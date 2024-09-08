package com.threads.concurrency.Synchronization;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedBuffer {
    private int data = -1;
    private boolean hasData = false;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    
    /** 
     * @param value
     * @throws InterruptedException
     */
    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (hasData) {
                condition.await();  // Wait until the buffer is empty
            }
            data = value;
            hasData = true;
            System.out.println("Produced: " + data);
            condition.signalAll();  // Notify the consumer
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (!hasData) {
                condition.await();  // Wait until the buffer has data
            }
            System.out.println("Consumed: " + data);
            hasData = false;
            condition.signalAll();  // Notify the producer
        } finally {
            lock.unlock();
        }
    }
}

public class LockConditionExample {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Runnable producerTask = () -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    buffer.produce(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumerTask = () -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    buffer.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread producerThread = new Thread(producerTask);
        Thread consumerThread = new Thread(consumerTask);

        producerThread.start();
        consumerThread.start();
    }
}
