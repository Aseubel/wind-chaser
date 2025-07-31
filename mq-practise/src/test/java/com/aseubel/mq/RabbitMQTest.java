package com.aseubel.mq;


import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/7/17 下午6:47
 */
@Slf4j
@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "hello.queue1")
    public void listen1(String message) throws InterruptedException{
        log.info("hello.queue1 消费者接收到消息：" + message);
    }

    @RabbitListener(queues = "myqueue")
    public void listen2(String message) throws InterruptedException{
        log.info("myqueue消费者 2 接收到消息：" + message);
    }

    @Test
    public void simpleSend() throws InterruptedException {
        String queueName = "myqueue";
        for (int i=1;i<=50;i++) {
            String message = "Hello worker, message_" + i;
            rabbitTemplate.convertAndSend(queueName, message);
            Thread.sleep(50);
        }
    }

    @Test
    public void testPublisherConfirm1() throws InterruptedException {
        CorrelationData cd = new CorrelationData(IdUtil.fastUUID());
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            if (ack) {
                if (data != null) {
                    log.info("消息发送成功：{}", data.getId());
                }
            } else {
                log.error("消息发送失败：{}", cause);
            }
        });
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.error("消息丢失：{}", returnedMessage);
        });
        rabbitTemplate.convertAndSend("amq.fanout", "", "Hello RabbitMQ, this is a test message.", cd);
        Thread.sleep(1000);
    }
}
