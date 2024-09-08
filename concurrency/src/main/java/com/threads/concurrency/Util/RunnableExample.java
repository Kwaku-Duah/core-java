package com.threads.concurrency.Util;

public class RunnableExample implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(500); // Pause for 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        RunnableExample task = new RunnableExample();
        Thread thread1 = new Thread(task, "Runnable-Thread-1");
        Thread thread2 = new Thread(task, "Runnable-Thread-2");
        thread1.start();
        thread2.start();
    }
}
