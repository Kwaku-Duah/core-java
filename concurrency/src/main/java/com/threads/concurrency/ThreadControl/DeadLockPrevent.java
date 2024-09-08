package com.threads.concurrency.ThreadControl;

class DeadlockPreventionExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void thread1Task() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock 1...");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (lock2) {
                System.out.println("Thread 1: Holding lock 1 & 2...");
            }
        }
    }

    public void thread2Task() {
        // Ensuring locks are acquired in the same order to prevent deadlock
        synchronized (lock1) {
            System.out.println("Thread 2: Holding lock 1...");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 1 & 2...");
            }
        }
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        DeadlockPreventionExample example = new DeadlockPreventionExample();

        Thread t1 = new Thread(example::thread1Task);
        Thread t2 = new Thread(example::thread2Task);

        t1.start();
        t2.start();
    }
}
