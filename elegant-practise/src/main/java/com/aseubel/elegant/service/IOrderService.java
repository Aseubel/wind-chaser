package com.aseubel.elegant.service;

import com.aseubel.elegant.order.OrderRequest;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:50
 */
public interface IOrderService {

    void handleOrder(OrderRequest orderRequest);

}
