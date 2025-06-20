package com.aseubel.designpattern.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:35
 */
public class JdkInvocationHandler implements InvocationHandler {

    private final Object target;

    public JdkInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after invoke " + method.getName());
        return result;
    }
}
