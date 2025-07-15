package com.aseubel.designpattern.ymxc.observe;

import com.aseubel.designpattern.ymxc.Order;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:37
 * @description 订单服务接口
 */
public interface OrderService {
    // 三个需要实现的监听事件
    void registerPaymentStateListen(PaymentStateListen listen);

    void unregisterPaymentStateListen(PaymentStateListen listen);

    void notifyPaymentStateListens(Order order);
}
