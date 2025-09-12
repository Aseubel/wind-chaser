package com.aseubel.dynamictp;

import org.dromara.dynamictp.spring.annotation.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Aseubel
 * @date 2025/9/12 上午9:48
 */
@EnableDynamicTp
@SpringBootApplication
public class DynamictpApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamictpApplication.class, args);
    }
}
