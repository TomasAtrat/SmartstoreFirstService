package com.smartstore.smartstorewebservice.microservices.tasks.controllers;

import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.entities.Task;
import com.smartstore.smartstorewebservice.microservices.tasks.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping("/")
    public List<HTTPAnswer> addTasks(@RequestBody final List<Task> tasks) {
        List<HTTPAnswer> answers = new ArrayList<>();
        tasks.forEach(task-> answers.add(taskService.add(task)));
        return answers;
    }

    @PostMapping("/task")
    public HTTPAnswer addTask(@RequestBody final Task task) {
        return taskService.add(task);
    }

}
