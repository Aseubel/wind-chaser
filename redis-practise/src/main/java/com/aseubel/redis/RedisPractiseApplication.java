package com.aseubel.redis;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author Aseubel
 * @date 2025/7/2 下午7:32
 */
@SpringBootApplication
public class RedisPractiseApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisPractiseApplication.class, args);
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}
