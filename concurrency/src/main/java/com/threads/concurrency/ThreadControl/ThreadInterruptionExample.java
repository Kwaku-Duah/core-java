package com.threads.concurrency.ThreadControl;




class InterruptibleThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread running: " + i);
                Thread.sleep(1000);  // Simulate long-running task
            }
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted!");
        }
    }
}

public class ThreadInterruptionExample {
    public static void main(String[] args) throws InterruptedException {
        InterruptibleThread thread = new InterruptibleThread();
        thread.start();

        Thread.sleep(3000);  // Main thread sleeps for 3 seconds
        thread.interrupt();  // Interrupt the running thread
    }
}
