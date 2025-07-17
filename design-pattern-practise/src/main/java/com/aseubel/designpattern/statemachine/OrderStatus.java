package com.aseubel.designpattern.statemachine;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:26
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    //待支付,待发货,待收货,已完成
    WAIT_PAYMENT(1, "待支付"),
    WAIT_DELIVER(2, "待发货"),
    WAIT_RECEIVE(3, "待收货"),
    FINISH(4, "已完成");
    private final Integer key;
    private final String desc;

    public static OrderStatus getByKey(Integer key) {
        for (OrderStatus e : values()) {
            if (e.getKey().equals(key)) {
                return e;
            }
        }
        throw new RuntimeException("enum not exists.");
    }
}