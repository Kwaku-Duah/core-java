package com.threads.concurrency.ProducerConsumer;

class SharedBuffer {
    private int value;
    private boolean hasValue = false;

    
    /** 
     * @param newValue
     * @throws InterruptedException
     */
    public synchronized void produce(int newValue) throws InterruptedException {
        while (hasValue) {
            wait();  // Wait if the buffer is full
        }
        value = newValue;
        hasValue = true;
        System.out.println("Produced: " + value);
        notify();  // Notify the consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasValue) {
            wait();  // Wait if the buffer is empty
        }
        System.out.println("Consumed: " + value);
        hasValue = false;
        notify();  // Notify the producer
    }
}

public class BasicProducerConsumerExample {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.produce(i);
                    Thread.sleep(100);  // Simulate time taken to produce
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.consume();
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

