package com.example.springboot_rabbitmq_tutorial.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKeyName;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

    // use rabbit template to send the messages
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        logger.info("send message:{}", message);
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }

}
