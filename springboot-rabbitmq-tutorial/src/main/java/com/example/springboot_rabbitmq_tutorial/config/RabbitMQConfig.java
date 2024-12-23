package com.example.springboot_rabbitmq_tutorial.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKeyName;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKeyName;

    //spring bean for queue to store json messages
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
    }
    //Spring bean for rabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    //bean for rabbit exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    //binding between queue and exchange using routing key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKeyName);
    }

    //binding between jsonqueue and exchange using routing key
    @Bean
    public Binding bindingJson() {
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJsonKeyName);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
    // These 3 are autoconfigured by spring
}
