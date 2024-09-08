package com.threads.concurrency.ThreadControl;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 20;
    private int[] arr;
    private int start, end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    
    /** 
     * @return Integer
     */
    @Override
    protected Integer compute() {
        if (end - start < THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(arr, start, mid);
            SumTask rightTask = new SumTask(arr, mid, end);
            
            leftTask.fork();  // Execute left task asynchronously
            return rightTask.compute() + leftTask.join();  // Compute right task and join left task's result
        }
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;  // Array of numbers from 1 to 100
        }

        try (ForkJoinPool pool = new ForkJoinPool()) {
            SumTask task = new SumTask(arr, 0, arr.length);
            
            int result = pool.invoke(task);
            System.out.println("Sum of array: " + result);
        }
    }
}
