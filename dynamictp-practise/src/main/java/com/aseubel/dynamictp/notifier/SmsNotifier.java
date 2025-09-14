package com.aseubel.dynamictp.notifier;

import org.dromara.dynamictp.common.entity.NotifyPlatform;
import org.dromara.dynamictp.common.notifier.AbstractNotifier;

/**
 * @author Aseubel
 * @date 2025/9/14 上午12:01
 */
public class SmsNotifier extends AbstractNotifier {

    private final SmsClient smsClient;

    public SmsNotifier(SmsClient smsClient) {
        this.smsClient = smsClient;
    }

    @Override
    public String platform() {
        return "sms";
    }

    @Override
    protected void send0(NotifyPlatform platform, String content) {
        String[] receivers = platform.getReceivers().split(",");
        smsClient.send(platform.getSecret(), receivers, content);
    }
}
