package com.aseubel.cache.service;

import com.aseubel.cache.annotation.QueryCache;
import com.aseubel.cache.annotation.UpdateCache;
import com.aseubel.cache.entity.Order;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/8/9 上午10:01
 */
@Service
public class OrderService implements IOrderService {
    @Override
    @QueryCache(key = "#id")
    public Order getOrder(int id) {
        return new Order(id, "order name", 100.0);
    }

    @Override
    @UpdateCache(key = "#updatedOrder.id")
    public Order updateOrder(Order updatedOrder) {
        return updatedOrder;
    }

    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public void deleteAllOrders() {

    }
}
