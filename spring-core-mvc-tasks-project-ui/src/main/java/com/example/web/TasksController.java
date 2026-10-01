package com.example.web;

import com.example.Task;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final ArrayList<Task> tasks = new ArrayList<>();

    TasksController() {
        tasks.add(new Task(1, "Default task #1", true, "LOW"));
        tasks.add(new Task(2, "Default task #2", true, "MEDIUM"));
        tasks.add(new Task(3, "Default task #3", false, "HIGH"));
    }

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "high") String priority, Model model) {
        List<Task> filteredTasks = tasks.stream()
                .filter((task) -> task.getPriority().equalsIgnoreCase(priority))
                .toList();

        model.addAttribute("tasks", filteredTasks);
        model.addAttribute("priority", priority);
        return "tasks";
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task(0, "", false, "LOW"));
        return "new-task";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result , Model model) {

        if (result.hasErrors()) {
            return "new-task";
        }

        tasks.add(new Task(nextId(), task.getName(), task.isCompleted(), task.getPriority()));
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable int id, Model model) {
        Task task = tasks.stream()
                .filter((currentTask) -> currentTask.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));

        model.addAttribute("task", task);
        return "task";
    }

    @ExceptionHandler(TaskNotFoundException.class)

    public String handleNotFound(TaskNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/not-found";
    }

    private int nextId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }
}
