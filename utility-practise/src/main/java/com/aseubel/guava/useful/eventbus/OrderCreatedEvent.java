package com.aseubel.guava.useful.eventbus;

/**
 * 定义事件
 * @author Aseubel
 * @date 2025/10/5 上午12:04
 */
public class OrderCreatedEvent {
    String orderId;
    public OrderCreatedEvent(String orderId) {
        this.orderId = orderId;
    }
}
