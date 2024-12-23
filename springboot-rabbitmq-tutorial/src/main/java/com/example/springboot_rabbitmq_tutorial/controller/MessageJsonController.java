package com.example.springboot_rabbitmq_tutorial.controller;

import com.example.springboot_rabbitmq_tutorial.dto.User;
import com.example.springboot_rabbitmq_tutorial.producer.RabbitMQJsonProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) throws JsonProcessingException {
        jsonProducer.sendMessageJson(user);
        return ResponseEntity.ok("Json Message sent to RabbitMQ");
    }
}
