package com.aseubel.jpa.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Aseubel
 * @date 2025/6/15 下午4:02
 */
public class MyAuditor implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        // 从某处获取用户id
        return Optional.of(1L);
    }
}
