package com.aseubel.guava.useful.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 定义监听器
 * @author Aseubel
 * @date 2025/10/5 上午12:03
 */
public class OrderListener {
    @Subscribe
    public void handle(OrderCreatedEvent event) {
        System.out.println("监听到订单创建事件：" + event.orderId);
    }
}
