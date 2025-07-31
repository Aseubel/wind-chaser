package com.aseubel.mq.rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Aseubel
 * @date 2025/7/17 下午6:45
 */
@Component
public class SimplePublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(String message) {
        String queueName = "myQueue";
        String exchangeName = "myExchange";
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
