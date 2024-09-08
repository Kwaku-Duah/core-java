package com.threads.concurrency.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducerConsumerExample {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);  // Buffer size of 10

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.put(i);  // Automatically blocks if the queue is full
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);  // Simulate time taken to produce
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Integer value = queue.take();  // Automatically blocks if the queue is empty
                    System.out.println("Consumed: " + value);
                    Thread.sleep(200);  // Simulate time taken to consume
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
