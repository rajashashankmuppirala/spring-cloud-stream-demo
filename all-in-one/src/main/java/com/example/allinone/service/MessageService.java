package com.example.allinone.service;

import dto.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class MessageService {

    @Bean
    public Function<MyTask, MyTask> processCreatedTasks() {
        return (receivedTask) -> {
            log.info("Received {}", receivedTask);
            MyTask processingTask = receivedTask;
            processingTask.setStatus("processing");
            processingTask.setDetails("processing test task");
            return processingTask;
        };
    }

    @Bean
    public Function<MyTask, MyTask> processTasks() {
        return (receivedTask) -> {
            log.info("Received {}", receivedTask);
            MyTask completedTask = receivedTask;
            completedTask.setStatus("completed");
            completedTask.setDetails("completed test task");
            return completedTask;
        };
    }

    @Bean
    public java.util.function.Consumer<Message<MyTask>> onComplete() {
        return message -> {
            System.out.println("Received message onComplete: " + message.getPayload());
        };
    }
}
