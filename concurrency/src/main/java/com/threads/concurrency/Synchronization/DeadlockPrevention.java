package com.threads.concurrency.Synchronization;

class DeadlockPreventionExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void task1() {
        synchronized (lock1) {
            System.out.println("Task 1: Holding lock 1...");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (lock2) {
                System.out.println("Task 1: Holding lock 1 & lock 2...");
            }
        }
    }

    public void task2() {
        // Ensure that locks are acquired in the same order (lock1 -> lock2)
        synchronized (lock1) {
            System.out.println("Task 2: Holding lock 1...");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (lock2) {
                System.out.println("Task 2: Holding lock 1 & lock 2...");
            }
        }
    }
}

public class DeadlockPrevention {
    public static void main(String[] args) {
        DeadlockPreventionExample example = new DeadlockPreventionExample();
        
        Thread t1 = new Thread(example::task1);
        Thread t2 = new Thread(example::task2);
        
        t1.start();
        t2.start();
    }
}
