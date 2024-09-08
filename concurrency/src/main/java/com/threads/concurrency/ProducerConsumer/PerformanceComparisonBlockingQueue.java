package com.threads.concurrency.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PerformanceComparisonBlockingQueue {
    
    /** 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        long startTime = System.nanoTime();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    queue.take();
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
        System.out.println("BlockingQueue implementation time: " + (endTime - startTime) + " ns");
    }
}
