package com.aseubel.designpattern.proxy.stc;

import com.aseubel.designpattern.proxy.SmsService;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:30
 */
public class SmsProxy implements SmsService {

    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        System.out.println("before send message...");
        smsService.send(message);
        System.out.println("after send message...");
        return message;
    }
}
