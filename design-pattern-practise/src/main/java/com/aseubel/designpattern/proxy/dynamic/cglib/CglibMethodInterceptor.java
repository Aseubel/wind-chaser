package com.aseubel.designpattern.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:43
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibMethodInterceptor intercept method: " + method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("CglibMethodInterceptor result: " + result);
        return result;
    }
}
