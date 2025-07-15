package com.aseubel.designpattern.ymxc.service;

import com.aseubel.designpattern.ymxc.Order;
import com.aseubel.designpattern.ymxc.observe.OrderService;
import com.aseubel.designpattern.ymxc.observe.PaymentStateListen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:43
 */
// 具体被观察者
public class OrderServiceImpl implements OrderService {
    private List<PaymentStateListen> paymentStateListens = new ArrayList<>();


    public void processOrder(Order order) {
        // 业务处理
        process(order);
        // 通知监听者
        notifyPaymentStateListens(order);
    }

    @Override
    public void registerPaymentStateListen(PaymentStateListen listen) {
        paymentStateListens.add(listen);
    }

    @Override
    public void unregisterPaymentStateListen(PaymentStateListen listen) {
        paymentStateListens.remove(listen);
    }

    @Override
    public void notifyPaymentStateListens(Order order) {
        // 通知全部注册的监听者
        handlers.forEach(handler -> handler.stateChanged(order));
    }
    // 观察者具体实现，以及注册观察者等代码略
}