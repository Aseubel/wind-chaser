package com.aseubel.designpattern.ymxc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aseubel
 * @date 2025/7/14 下午7:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayContext {
    private Object request;
    private Object model;
    private Boolean isNeedSign;
    private String signMessage;

    public String assembledContext() {
        return "request: " + request + " model: " + model + " isNeedSign: " + isNeedSign + " signMessage: " + signMessage;
    }
}
