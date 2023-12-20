package ru.headsandhands.homeservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @KafkaListener(topics = "user-topic", groupId = "test")
    public void Listener1(String message) {
        System.out.println("Message: " + message);
    }

    @KafkaListener(topics = "home-deleted", groupId = "test")
    public void Listener2(String message) {
        System.out.println("Message: " + message);
    }

}