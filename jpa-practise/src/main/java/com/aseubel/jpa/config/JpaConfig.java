package com.aseubel.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Aseubel
 * @date 2025/6/15 下午4:04
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.aseubel.jpa.repository")
@EntityScan(basePackages = "com.aseubel.jpa.entity")
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<Long> auditorAware() {
        return new MyAuditor();
    }
}
