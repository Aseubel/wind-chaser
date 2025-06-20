package com.aseubel.designpattern.proxy.dynamic.cglib;

import cn.hutool.aop.aspects.Aspect;

import java.lang.reflect.Method;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:53
 */
public class SmsAspect implements Aspect {

    @Override
    public boolean before(Object o, Method method, Object[] objects) {
        System.out.println("aspect, before send message");
        // true 执行原方法， false 不执行原方法
        return true;
    }

    @Override
    public boolean after(Object o, Method method, Object[] objects, Object o1) {
        System.out.println("aspect, after send message");
        // true 继续执行其他可能的Aspect， false 终止执行其他Aspect
        return true;
    }

    @Override
    public boolean afterException(Object o, Method method, Object[] objects, Throwable throwable) {
        System.out.println("aspect, after exception");
        // true 继续执行其他可能的操作
        return false;
    }
}
