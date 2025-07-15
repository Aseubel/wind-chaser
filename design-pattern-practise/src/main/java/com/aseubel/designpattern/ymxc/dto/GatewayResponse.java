package com.aseubel.designpattern.ymxc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:12
 */
@Builder
@Data
@ToString
public class GatewayResponse {
    private boolean success;
    private String errorMessage;
    private String responseContext;
}
