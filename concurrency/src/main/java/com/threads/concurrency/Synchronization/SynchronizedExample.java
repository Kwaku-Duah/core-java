package com.threads.concurrency.Synchronization;

class SharedResource {
    private int count = 0;

    // Synchronized method
    public synchronized void increment() {
        count++;
    }

    // Synchronized block
    public void synchronizedBlockIncrement() {
        synchronized (this) {
            count++;
        }
    }

    
    /** 
     * @return int
     */
    public int getCount() {
        return count;
    }
}

public class SynchronizedExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                resource.increment();  // Using synchronized method
                resource.synchronizedBlockIncrement();  // Using synchronized block
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + resource.getCount());
    }
}

