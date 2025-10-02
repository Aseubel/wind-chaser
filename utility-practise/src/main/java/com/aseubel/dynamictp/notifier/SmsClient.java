package com.aseubel.dynamictp.notifier;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aseubel
 * @date 2025/9/13 下午11:58
 */
@Slf4j
public class SmsClient {

    public void send(String secret, String[] receivers, String content) {
        log.info("send sms, secret: {}, receivers: {}, content: {}", secret, receivers, content);
    }
}
