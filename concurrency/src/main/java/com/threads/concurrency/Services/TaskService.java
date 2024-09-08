package com.threads.concurrency.Services;

import com.threads.concurrency.Entities.*;
import com.threads.concurrency.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // ConcurrentHashMap for caching tasks
    private final Map<Long, Task> taskCache = new ConcurrentHashMap<>();

    
    /** 
     * @param task
     * @return Task
     */
    // Method to save a new task
    public Task saveTask(Task task) {
        Task savedTask = taskRepository.save(task);
        taskCache.put(savedTask.getId(), savedTask); // Cache the saved task
        return savedTask;
    }

    // Method to retrieve all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Method to retrieve a task by ID
    public Task getTaskById(Long id) {
        // First check the cache
        Task cachedTask = taskCache.get(id);
        if (cachedTask != null) {
            return cachedTask;
        }
        // If not found in cache, retrieve from repository
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskCache.put(id, task); // Cache the retrieved task
        }
        return task;
    }

    // Method to delete a task by ID
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
        taskCache.remove(id); // Remove from cache
    }

    // Asynchronous method to perform a long-running task (simulating concurrency)
    @Async
    public CompletableFuture<Task> processTaskAsync(Long taskId) {
        // Simulate a long-running task processing
        try {
            Thread.sleep(5000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Task task = getTaskById(taskId);
        if (task != null) {
            // Perform some processing on the task
            task.setStatus("PROCESSED");
            taskRepository.save(task);
            taskCache.put(taskId, task); // Update cache
        }

        return CompletableFuture.completedFuture(task);
    }
}
