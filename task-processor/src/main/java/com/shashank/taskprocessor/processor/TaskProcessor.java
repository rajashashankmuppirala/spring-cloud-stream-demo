package com.shashank.taskprocessor.processor;

import dto.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
public class TaskProcessor {

    @Bean
    public Function<MyTask, MyTask> processTasks(){
        return (receivedTask) -> {
            log.info("Received {}", receivedTask);
            MyTask completedTask = receivedTask;
            completedTask.setStatus("completed");
            completedTask.setDetails("completed test task");
            return completedTask;
        };
    }
}
