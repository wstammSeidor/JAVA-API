package com.walter.javataskapi.controller;

import com.walter.javataskapi.model.Task;
import com.walter.javataskapi.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api/tasks") // Base path for all task-related endpoints
public class TaskController {

    private final TaskRepository repository;

    // Constructor-based dependency injection
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    // GET /api/tasks - Retrieve all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // POST /api/tasks - Create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    // GET /api/tasks/{id} - Retrieve a task by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /api/tasks/{id} - Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return repository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setPriority(updatedTask.getPriority());
            repository.save(task);
            return ResponseEntity.ok(task);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/tasks/{id} - Delete a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }

    // GET /api/tasks/priority/{level} - Filter tasks by priority level
    @GetMapping("/priority/{level}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String level) {
        List<Task> allTasks = repository.findAll();
        List<Task> filteredTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getPriority() != null && task.getPriority().equalsIgnoreCase(level)) {
                filteredTasks.add(task);
            }
        }

        return ResponseEntity.ok(filteredTasks);
    }

    // GET /api/tasks/export - Export all tasks to a local file
    @GetMapping("/export")
    public ResponseEntity<String> exportTasksToFile() {
        List<Task> tasks = repository.findAll();
        File file = new File("tasks_export.txt");

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task task : tasks) {
                writer.println(task.getId() + "," + task.getTitle() + "," + task.getDescription() + "," + task.getDueDate() + "," + task.getPriority());
            }
            return ResponseEntity.ok("Tasks successfully exported to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting tasks.");
        }
    }
}

