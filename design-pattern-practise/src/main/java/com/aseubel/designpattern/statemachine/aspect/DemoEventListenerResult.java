package com.aseubel.designpattern.statemachine.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Aseubel
 * @date 2025/7/17 下午1:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DemoEventListenerResult {
    String key() default "";
}
