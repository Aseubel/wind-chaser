package com.aseubel.designpattern.proxy;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:29
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("Sending message: " + message);
        return message;
    }
}
