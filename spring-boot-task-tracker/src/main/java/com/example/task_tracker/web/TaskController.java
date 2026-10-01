package com.example.task_tracker.web;

import com.example.task_tracker.data.TasksRepo;
import com.example.task_tracker.model.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TasksRepo repo;

    @Value("${tasktracker.max-tasks}")
    private int maxSize;

    TaskController(TasksRepo repo) {
        this.repo = repo;
    }


    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {

        if (repo.getSize() >= maxSize) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        repo.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false, defaultValue = "false")
            boolean completed,
            @RequestParam(required = false, defaultValue = "${tasktracker.default-page-size}")
            int limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.getTasks(limit, completed));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        Task task = repo.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }

    }

    // put can add a completely new resource
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task task) {

        Task existingTask = repo.getTask(id);

        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }

        Task updatedTask = repo.updateTask(id, task);

        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Task> markCompleted(@PathVariable int id) {

        Task task = repo.getTask(id);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (task.isCompleted()) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        task.setCompleted(true);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable int id) {
        Task task = repo.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        repo.removeTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}
