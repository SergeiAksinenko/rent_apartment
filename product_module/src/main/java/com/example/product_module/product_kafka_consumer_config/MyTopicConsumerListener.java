package com.example.product_module.product_kafka_consumer_config;


import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.kafka.support.KafkaHeaders.GROUP_ID;

@Component
@Getter
public class MyTopicConsumerListener {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "my_topic",groupId = "kafka_groupId")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }
}
