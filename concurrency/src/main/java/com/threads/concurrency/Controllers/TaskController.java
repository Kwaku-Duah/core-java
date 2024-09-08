package com.threads.concurrency.Controllers;


import com.threads.concurrency.Entities.*;
import com.threads.concurrency.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    
    /** 
     * @param task
     * @return ResponseEntity<String>
     */
    // Endpoint to create a new task
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>("Task created with ID: " + savedTask.getId() + " - Multithreading done", HttpStatus.CREATED);
    }

    // Endpoint to get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Endpoint to get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>("Task found: " + task.getName() + " - Multithreading done", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found - Multithreading done", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>("Task deleted - Multithreading done", HttpStatus.OK);
    }

    // Endpoint to process a task asynchronously (demonstrating concurrency)
    @GetMapping("/process/{id}")
    public CompletableFuture<ResponseEntity<String>> processTaskAsync(@PathVariable Long id) {
        return taskService.processTaskAsync(id)
                .thenApply(task -> {
                    if (task != null) {
                        return new ResponseEntity<>("Task processed asynchronously with ID: " + task.getId() + " - Concurrent tasks run done", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Task not found - Concurrent tasks run done", HttpStatus.NOT_FOUND);
                    }
                });
    }
}
