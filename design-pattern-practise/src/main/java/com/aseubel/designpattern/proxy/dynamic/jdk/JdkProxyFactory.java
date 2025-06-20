package com.aseubel.designpattern.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:33
 */
public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JdkInvocationHandler(target)
        );
    }
}
