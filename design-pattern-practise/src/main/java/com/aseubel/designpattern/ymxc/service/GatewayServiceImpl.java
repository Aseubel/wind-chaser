package com.aseubel.designpattern.ymxc.service;

import com.aseubel.designpattern.ymxc.GatewayContext;
import com.aseubel.designpattern.ymxc.GatewayHandler;
import com.aseubel.designpattern.ymxc.GatewayHandlerFactory;
import com.aseubel.designpattern.ymxc.dto.GatewayRequest;
import com.aseubel.designpattern.ymxc.dto.GatewayResponse;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:10
 */
public class GatewayServiceImpl implements GatewayService {
    @Override
    public GatewayResponse process(GatewayRequest request) {
        GatewayContext context = buildGatewayContext(request);

        // 获取责任链, 依次执行
        List<GatewayHandler> handlers = GatewayHandlerFactory.getHandlers();
        handlers.forEach(handler -> handler.process(context));

        return convertResponse(context);
    }

    private GatewayContext buildGatewayContext(GatewayRequest request) {
        return null;
    }

    private GatewayResponse convertResponse(GatewayContext context) {
        return GatewayResponse.builder()
                .success(true)
                .errorMessage("")
                .responseContext(context.assembledContext())
                .build();
    }
}
