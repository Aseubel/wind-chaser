package com.aseubel.guava.useful.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author Aseubel
 * @date 2025/10/5 上午12:02
 */
public class EventBusTests {
    public static void main(String[] args) {
        // 使用事件总线
        EventBus eventBus = new EventBus();
        eventBus.register(new OrderListener());
        eventBus.post(new OrderCreatedEvent("ORD-001"));
    }

}
