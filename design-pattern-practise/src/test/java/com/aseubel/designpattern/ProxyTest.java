package com.aseubel.designpattern;

import com.aseubel.designpattern.proxy.SmsService;
import com.aseubel.designpattern.proxy.dynamic.cglib.CglibProxyFactory;
import com.aseubel.designpattern.proxy.dynamic.cglib.SmsAspect;
import com.aseubel.designpattern.proxy.dynamic.jdk.JdkProxyFactory;
import com.aseubel.designpattern.proxy.stc.SmsProxy;
import com.aseubel.designpattern.proxy.SmsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:31
 */
@SpringBootTest
public class ProxyTest {

    /**
     * 静态代理
     */
    @Test
    public void staticProxyTest() {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("Hello World!");
    }

    /**
     * jdk动态代理
     * 缺点是只能代理实现了接口的类
     * 因为Proxy的newProxyInstance方法，需要传入代理类的接口
     */
    @Test
    public void jdkProxyTest() {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("Hello World!");
    }

    /**
     * CGLIB 动态代理
     * 需要引入cglib包
     * jdk9以上需要加jvm参数：--add-opens java.base/java.lang=ALL-UNNAMED
     * 因为cglib在jdk9引入模块化系统后，java.lang下的部分类和方法不开放
     * 也就是不能访问java.lang.ClassLoader的一些内部方法，所以需要加上--add-opens参数
     */
    @Test
    public void cglibProxyTest() {
        SmsService smsService = (SmsService) CglibProxyFactory.getProxy(SmsServiceImpl.class);
        smsService.send("Hello World!");
    }

    /**
     * 使用hutool的工厂类
     * 可以传入自定义的切面类
     */
    @Test
    public void hutoolDynamicProxyTest() {
        SmsService smsService = cn.hutool.aop.proxy.CglibProxyFactory.createProxy(new SmsServiceImpl(), SmsAspect.class);
        smsService.send("Hello World!");
    }
}
