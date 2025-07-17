package com.aseubel.designpattern.statemachine.service;

import com.aseubel.designpattern.statemachine.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:49
 */
public interface OrderService extends IService<Order> {
    Order create(Order order);

    Order pay(Long id);

    Order deliver(Long id);

    Order receive(Long id);

    Order reject(Long id);
}
