package com.threads.concurrency.ProducerConsumer;

class SynchronizedBuffer {
    private boolean hasValue = false;

    
    /** 
     * @param newValue
     * @throws InterruptedException
     */
    public synchronized void produce(int newValue) throws InterruptedException {
        while (hasValue) {
            wait();
        }
        hasValue = true;
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasValue) {
            wait();
        }
        hasValue = false;
        notify();
    }
}

public class PerformanceComparisonSynchronized {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedBuffer buffer = new SynchronizedBuffer();
        long startTime = System.nanoTime();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    buffer.produce(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    buffer.consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        long endTime = System.nanoTime();
        System.out.println("Synchronized implementation time: " + (endTime - startTime) + " ns");
    }
}
