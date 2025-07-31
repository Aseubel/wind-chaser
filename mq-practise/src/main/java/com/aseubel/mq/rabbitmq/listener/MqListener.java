package com.aseubel.mq.rabbitmq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Aseubel
 * @date 2025/7/18 下午8:25
 */
@Component
public class MqListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1", durable = "true"),
            exchange = @Exchange(name = "wind-chaser.fanout", type = ExchangeTypes.FANOUT),
            key = {"key1", "key2"}
    ))
    public void listenFanout1(String msg) {

    }
}
