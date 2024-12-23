package com.example.order_service.producer;

import com.example.order_service.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String orderRoutingKey;

    @Value("${rabbitmq.binding.email.routing.key}")
    private String emailRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {
        logger.info("Order Event sent to OrderQueue : " + orderEvent.toString());
        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderEvent);

        //send an order event to email queue
        logger.info("Order Event sent to EmailQueue : " + orderEvent.toString());
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);
    }
}
