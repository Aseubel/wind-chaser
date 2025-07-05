package com.aseubel.elegant.service;

import com.aseubel.elegant.order.OrderModel;
import org.springframework.plugin.core.Plugin;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:50
 */
public interface IOrderModelHandler extends Plugin<OrderModel> {

    void handleOrderModel(OrderModel orderModel);

}
