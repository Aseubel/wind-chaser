package com.aseubel.designpattern.statemachine;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:28
 */
public enum OrderEvent {
    // 支付，发货，确认收货,退货
    PAYED,
    DELIVERED,
    RECEIVED,
    REJECTED
}
