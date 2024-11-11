package com.shashank.taskconsumer.consumer;

import dto.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class TaskCompletionHandler {

    @Bean
    public Consumer<Message<MyTask>> onComplete() {
        return message -> {
           log.info("Consumer 1 received message: " + message.getPayload());
        };
    }
}
