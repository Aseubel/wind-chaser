package com.aseubel.elegant.service.impl;

import com.aseubel.elegant.order.OrderModel;
import com.aseubel.elegant.service.IOrderModelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:52
 */
@Slf4j
@Service
public class MessageHandler implements IOrderModelHandler {

    @Override
    public void handleOrderModel(OrderModel orderModel) {
        log.info("消息发送");
    }

    @Override
    public boolean supports(OrderModel orderModel) {
        return true;
    }
}
