package com.aseubel.designpattern.ymxc;

import com.aseubel.designpattern.ymxc.handler.ParameterTransformHandler;
import com.aseubel.designpattern.ymxc.handler.SignatureHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/7/14 下午7:05
 * @description 简单工厂模式，用于创建处理请求的处理器
 */
public class GatewayHandlerFactory {
    @Getter
    private static final List<GatewayHandler> handlers = new ArrayList<>();

    static {
        handlers.add(new ParameterTransformHandler());
        handlers.add(new SignatureHandler());
        // add more handlers here
    }

}
