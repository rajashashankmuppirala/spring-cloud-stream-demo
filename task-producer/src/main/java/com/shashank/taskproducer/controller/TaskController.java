package com.shashank.taskproducer.controller;

import com.shashank.taskproducer.service.TaskService;
import dto.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createTask(){
        try{
            MyTask newTask = new MyTask();
            newTask.setId(UUID.randomUUID().toString());
            newTask.setStatus("created");
            newTask.setDetails("test task created");


            log.info("sending task {} to the topic", newTask);
            taskService.createTask(newTask);
            return ResponseEntity.ok("created task : " + newTask.getId());

        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
