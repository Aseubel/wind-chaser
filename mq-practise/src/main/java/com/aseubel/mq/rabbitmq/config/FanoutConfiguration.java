package com.aseubel.mq.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aseubel
 * @date 2025/7/18 下午8:07
 */
@Configuration
public class FanoutConfiguration {
    @Bean
    public FanoutExchange fanoutExchange() {
//        ExchangeBuilder.fanoutExchange("fanoutExchange");
        return new FanoutExchange("wind-chaser.fanout");
    }

    @Bean
    public Queue fanoutQueue1() {
//         QueueBuilder.durable("fanout.queue1").build();
        return new Queue("fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
//         QueueBuilder.durable("fanout.queue2").build();
        return new Queue("fanout.queue2");
    }

    @Bean
    public Binding bindingExchangeQueue(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
