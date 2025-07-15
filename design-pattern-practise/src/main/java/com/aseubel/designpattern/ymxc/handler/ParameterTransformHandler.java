package com.aseubel.designpattern.ymxc.handler;

import com.aseubel.designpattern.ymxc.GatewayContext;
import com.aseubel.designpattern.ymxc.GatewayHandler;

/**
 * @author Aseubel
 * @date 2025/7/14 下午7:01
 * @description 内部参数转外部参数的处理器
 */
public class ParameterTransformHandler implements GatewayHandler {
    @Override
    public void process(GatewayContext context) {
        Object model = context.getModel();
        // 内部参数转外部参数
        maskPhone(model);
    }

    private void maskPhone(Object model) {
        System.out.println("Masking phone number");
    }
}
