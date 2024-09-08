package com.threads.concurrency.Util;

class LifeCycleThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is RUNNING");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is in WAITING state");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {
        LifeCycleThread thread = new LifeCycleThread();
        
        // NEW State
        System.out.println(thread.getState());

        // RUNNABLE State
        thread.start();
        System.out.println(thread.getState());

        // TIMED_WAITING state due to sleep
        Thread.sleep(500);
        System.out.println(thread.getState());

        // TERMINATED state after completion
        Thread.sleep(2000);
        System.out.println(thread.getState());
    }
}
