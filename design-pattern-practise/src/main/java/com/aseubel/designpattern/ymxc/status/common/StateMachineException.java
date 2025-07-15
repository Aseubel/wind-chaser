package com.aseubel.designpattern.ymxc.status.common;

import com.aseubel.designpattern.ymxc.status.PaymentEvent;
import com.aseubel.designpattern.ymxc.status.PaymentStatus;
import lombok.Getter;

import java.io.Serial;

/**
 * @author Aseubel
 * @date 2025/7/15 下午8:29
 */
@Getter
public class StateMachineException extends RuntimeException {
    @Serial
    private final static long serialVersionUID = 1L;
    private final PaymentStatus status;
    private final PaymentEvent event;

    public StateMachineException(PaymentStatus status, PaymentEvent event, String message) {
        super(message);
        this.status = status;
        this.event = event;
    }
}
