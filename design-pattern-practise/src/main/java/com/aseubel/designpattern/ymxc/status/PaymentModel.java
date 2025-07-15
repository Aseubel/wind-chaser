package com.aseubel.designpattern.ymxc.status;

import com.aseubel.designpattern.ymxc.status.common.StateMachineException;
import lombok.Builder;

/**
 * 支付单模型
 */
@Builder
public class PaymentModel {
    /**
     * 其它所有字段省略
     */
    // 上次状态
    private PaymentStatus lastStatus;
    // 当前状态
    private PaymentStatus currentStatus;

    /**
     * 根据事件推进状态
     */
    public void transferStatusByEvent(PaymentEvent event) {
        // 根据当前状态和事件，去获取目标状态
        PaymentStatus targetStatus = PaymentStatus.getTargetStatus(currentStatus, event);
        // 如果目标状态不为空，说明是可以推进的
        if (targetStatus != null) {
            lastStatus = currentStatus;
            currentStatus = targetStatus;
            System.out.println("状态转换：" + lastStatus + " -> " + currentStatus);
        } else {
            // 目标状态为空，说明是非法推进，进入异常处理，这里只是抛出去，由调用者去具体处理
            throw new StateMachineException(currentStatus, event, "状态转换失败");
        }
    }
}