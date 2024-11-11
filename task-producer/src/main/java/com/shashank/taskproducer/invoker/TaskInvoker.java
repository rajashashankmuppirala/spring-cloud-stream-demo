package com.shashank.taskproducer.invoker;

import dto.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class TaskInvoker {

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

}
