package com.aseubel.cache.service;

import com.aseubel.cache.entity.Order;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/8/9 上午10:01
 */
public interface IOrderService {
    Order getOrder(int id);

    Order updateOrder(Order updatedOrder);

    void deleteOrder(int id);

    void deleteAllOrders();
}
