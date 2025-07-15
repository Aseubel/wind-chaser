package com.aseubel.designpattern.ymxc.status.service;

import com.aseubel.designpattern.ymxc.status.PaymentNotifyMessage;

/**
 * @author Aseubel
 * @date 2025/7/15 下午8:33
 */
public interface PaymentDomainService {
    void notify(PaymentNotifyMessage paymentNotifyMessage);
}
