package com.aseubel.lambda.actor;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/18 下午4:27
 */
@Getter
public abstract class AbstractActor implements Actor {
    private String name;
    private String role;
    public AbstractActor(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
