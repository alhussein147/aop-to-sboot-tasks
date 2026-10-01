package com.example.task_tracker.config;

import com.example.task_tracker.data.TasksRepo;
import com.example.task_tracker.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Random;

@Configuration
@Profile("dev")
public class AppDevConfig {
    private static final Logger log = LoggerFactory.getLogger(AppDevConfig.class);

    AppDevConfig() {
        log.info("Running dev profile");
    }

    @Bean
    CommandLineRunner seed(TasksRepo repo) {
        log.info("seeding tasks");
        return args -> {
            for (int i = 0; i < 25; i++) {
                repo.addTask(new Task(0, "Title " + i, "Some description", new Random().nextBoolean(), LocalDate.now()));
            }
        };
    }
}
