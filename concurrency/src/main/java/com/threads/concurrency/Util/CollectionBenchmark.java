/**
 * The `CollectionBenchmark` class compares the performance of `ConcurrentHashMap` and `HashMap` by
 * benchmarking put, get, and remove operations.
 */
package com.threads.concurrency.Util;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionBenchmark {

    private static final int NUM_OPERATIONS = 100000;

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        // Benchmark ConcurrentHashMap
        long concurrentHashMapTime = benchmarkConcurrentHashMap();
        System.out.println("ConcurrentHashMap time: " + concurrentHashMapTime + " ms");

        // Benchmark HashMap
        long hashMapTime = benchmarkHashMap();
        System.out.println("HashMap time: " + hashMapTime + " ms");
    }

    private static long benchmarkConcurrentHashMap() {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        long startTime = System.currentTimeMillis();

        // Perform put operations
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            concurrentHashMap.put(i, i);
        }

        // Perform get operations
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            concurrentHashMap.get(i);
        }

        // Perform remove operations
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            concurrentHashMap.remove(i);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static long benchmarkHashMap() {
        Map<Integer, Integer> hashMap = new HashMap<>();
        long startTime = System.currentTimeMillis();

        // Perform put operations
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            hashMap.put(i, i);
        }

        // Perform get operations
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            hashMap.get(i);
        }

        // Perform remove operations
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            hashMap.remove(i);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
