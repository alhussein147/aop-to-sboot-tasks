package com.example.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Task {
    private int id;

    @NotBlank(message = "name cannot be empty!")
    private String name;

    private boolean completed;

    @NotNull(message = "please select priority.")
    private String priority;

    public Task() {
    }

    public Task(int id, String name, boolean completed, String priority) {
        this.id = id;
        this.name = name;
        this.completed = completed;
        this.priority = priority;
    }
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
