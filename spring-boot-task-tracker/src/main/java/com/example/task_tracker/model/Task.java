package com.example.task_tracker.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class Task {

    Long id;
    @NotBlank(message = "title cannot be blank")
    String title;
    String description;
    boolean completed;
    LocalDate dueDate;

   public Task(long id, String title, String description, boolean completed, LocalDate dueDate) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setCompleted(completed);
        setDueDate(dueDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
