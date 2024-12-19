package com.example.allinone.service;


import dto.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TaskService {

    private final StreamBridge streamBridge;

    private static final String OUTPUT_BINDING = "createTaskEvent-out-0";

    public TaskService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void createTask(MyTask task){
        Map<String, Object> headers = new HashMap<>();
        headers.put("x-delay", 10000);
        Message<MyTask> message = MessageBuilder.withPayload(task)
                .setHeader("contentType", "application/json")
                .copyHeaders(headers)
                .build();
        log.info("before sending message");
        streamBridge.send(OUTPUT_BINDING, message);
    }
}
