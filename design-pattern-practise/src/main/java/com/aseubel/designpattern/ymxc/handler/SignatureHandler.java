package com.aseubel.designpattern.ymxc.handler;

import com.aseubel.designpattern.ymxc.GatewayContext;
import com.aseubel.designpattern.ymxc.GatewayHandler;

/**
 * @author Aseubel
 * @date 2025/7/14 下午7:03
 */
public class SignatureHandler implements GatewayHandler {
    @Override
    public void process(GatewayContext context) {
        if (!context.getIsNeedSign()) {
            System.out.println("不需要签名");
        }
        context.setSignMessage(sign(context.getSignPlainContext(), context.getInterfaceInfo().getSignConfig()));
    }
}
