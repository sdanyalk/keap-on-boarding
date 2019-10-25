package com.keap.taskapi.controller;

import com.keap.taskapi.model.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @RequestMapping(
            path = "/tasks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTasks() {
        Task task = new Task();
        task.setId(1);
        task.setDescription("complete the project");

        return ResponseEntity.ok(task);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
