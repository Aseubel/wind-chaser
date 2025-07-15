package com.aseubel.designpattern.ymxc.status;

import lombok.Data;

/**
 * @author Aseubel
 * @date 2025/7/15 下午8:33
 */
@Data
public class PaymentNotifyMessage {
    private String message;
    private PaymentStatus status;
    private PaymentEvent event;
}
