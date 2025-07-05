package com.aseubel.elegant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/7/5 下午7:59
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "filters.order")
public class OrderFilterConfigProperties {

    private Map<String, List<String>> configs;
}
