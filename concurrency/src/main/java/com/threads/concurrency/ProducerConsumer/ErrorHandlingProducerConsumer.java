package com.threads.concurrency.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHandlingProducerConsumer {
    private static final Logger logger = Logger.getLogger(ErrorHandlingProducerConsumer.class.getName());

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);  // Small buffer to simulate possible errors

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i == 7) throw new RuntimeException("Simulated error in producer");
                    queue.put(i);
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (RuntimeException e) {
                    logger.log(Level.SEVERE, "Error in producer: " + e.getMessage(), e);
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Integer value = queue.take();
                    if (value == 5) throw new RuntimeException("Simulated error in consumer");
                    System.out.println("Consumed: " + value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (RuntimeException e) {
                    logger.log(Level.SEVERE, "Error in consumer: " + e.getMessage(), e);
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
