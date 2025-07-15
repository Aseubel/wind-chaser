package com.aseubel.designpattern.ymxc.service;

import com.aseubel.designpattern.ymxc.dto.GatewayRequest;
import com.aseubel.designpattern.ymxc.dto.GatewayResponse;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:17
 */
public interface GatewayService {
    GatewayResponse process(GatewayRequest request);
}
