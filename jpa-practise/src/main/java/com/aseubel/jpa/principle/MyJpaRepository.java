package com.aseubel.jpa.principle;

import jakarta.persistence.EntityManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Aseubel
 * @date 2025/6/15 下午6:26
 */
public class MyJpaRepository implements InvocationHandler {

    EntityManager em;
    Class entityClass;

    public MyJpaRepository(EntityManager em, Class<?> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // jpa的统一实现类
        MyJpaProxy myJpaProxy = new MyJpaProxy(em, entityClass);
        // 获取jpa的统一实现类的方法
        Method realMethod = myJpaProxy.getClass().getMethod(method.getName(), method.getParameterTypes());
        // 调用jpa的统一实现类的方法
        return realMethod.invoke(myJpaProxy, args);
    }
}
