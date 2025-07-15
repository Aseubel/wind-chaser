package com.aseubel.designpattern.ymxc.observe;

import com.aseubel.designpattern.ymxc.Order;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:36
 * @description 支付状态监听器接口
 */
public interface PaymentStateListen {
    void stateChanged(Order order);
}
