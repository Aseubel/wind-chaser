package com.aseubel.designpattern.ymxc.status.service;

import com.aseubel.designpattern.ymxc.status.PaymentModel;
import com.aseubel.designpattern.ymxc.status.PaymentNotifyMessage;
import com.aseubel.designpattern.ymxc.status.common.StateMachineException;

/**
 * @author Aseubel
 * @date 2025/7/15 下午8:34
 */
public class PaymentDomainServiceImpl implements PaymentDomainService{
    @Override
    public void notify(PaymentNotifyMessage message) {
        PaymentModel paymentModel = buildPaymentModel(message);
        try {
            // 状态推进
            paymentModel.transferStatusByEvent(message.getEvent());
            savePaymentModel(paymentModel);
            // 其他业务逻辑
        } catch (StateMachineException e) {
            // 状态机异常处理
        } catch (Exception e) {
            // 其他异常处理
        }
    }

    private void savePaymentModel(PaymentModel paymentModel) {
        // 保存到数据库
        System.out.println("保存到数据库" + paymentModel.toString());
    }

    private PaymentModel buildPaymentModel(PaymentNotifyMessage paymentNotifyMessage) {
        return PaymentModel.builder().currentStatus(paymentNotifyMessage.getStatus()).build();
    }
}
