package com.aseubel.designpattern.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:44
 */
public class CglibProxyFactory {
    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new CglibMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
