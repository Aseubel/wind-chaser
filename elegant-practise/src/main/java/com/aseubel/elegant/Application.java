package com.aseubel.elegant;

import com.aseubel.elegant.service.IOrderModelHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.plugin.core.config.EnablePluginRegistries;

/**
 * @author Aseubel
 * @date 2025/7/6 下午1:21
 */
@SpringBootApplication
@EnablePluginRegistries(value = {IOrderModelHandler.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
