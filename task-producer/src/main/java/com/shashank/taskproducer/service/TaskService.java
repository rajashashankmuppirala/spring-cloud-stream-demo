package com.shashank.taskproducer.service;


import dto.MyTask;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final StreamBridge streamBridge;

    private static final String OUTPUT_BINDING = "createTaskEvent-out-0";

    public TaskService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void createTask(MyTask task){
        Message<MyTask> message = MessageBuilder.withPayload(task)
                .setHeader("contentType", "application/json")
                .build();
        streamBridge.send(OUTPUT_BINDING, message);
    }
}
