package com.example.task_tracker.data;

import com.example.task_tracker.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TasksRepo {
    private static final Logger log = LoggerFactory.getLogger(TasksRepo.class);

    private final List<Task> tasks = new ArrayList<>();

    public Task getTask(long id) {
        log.debug("Get Task with id {}", id);
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    public void removeTask(int id) {
        Task task = getTask(id);
        tasks.remove(task);
        log.debug("Removed Task with id {}", id);
    }

    public Task updateTask(Long id, Task task) {

        for (Task result : tasks) {

            if (result.getId().equals(id)) {

                result.setTitle(task.getTitle());
                result.setCompleted(task.isCompleted());
                result.setDescription(task.getDescription());
                result.setDueDate(task.getDueDate());
                return task;
            }
        }

        throw new RuntimeException("Task not found");
    }

    public List<Task> getTasks(Integer limit, Boolean filterByCompletion) {

        if (filterByCompletion) {
            log.debug("getting all tasks and filtering by completion");
            return tasks.stream().filter(task -> task.isCompleted()).limit(limit).toList();
        } else {
            log.debug("get all Tasks with the size of {}", limit);
            return tasks.stream().limit(limit).toList();
        }
    }

    public void addTask(Task task) {


        int id = tasks.size() + 1;
        task.setId((long) id);

        tasks.add(task);

        log.debug("Added Task with id {}", task.getId());
    }

    public int getSize() {
        log.debug("Get all tasks size");
        return tasks.size();
    }
}
