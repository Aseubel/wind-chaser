package com.aseubel.jpa.principle;

import com.aseubel.jpa.config.JpaConfig;
import com.aseubel.jpa.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;

/**
 * @author Aseubel
 * @date 2025/6/15 下午6:23
 */
public class MainStart {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(JpaConfig.class);

        // 获取 entityManager
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = ioc.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManager em = entityManagerFactoryBean.createNativeEntityManager(null);

        // 获取 pojo 类
        // 这里的可以拿到 UserRepository 的父接口
        ParameterizedType genericInterfaces = (ParameterizedType) UserRepository.class.getGenericInterfaces()[0];
        // 这里拿到接口的泛型参数
        Class<?> entityClass = (Class<?>) genericInterfaces.getActualTypeArguments()[0];

        UserRepository userRepository = (UserRepository) Proxy.newProxyInstance(
                UserRepository.class.getClassLoader(),
                new Class[]{UserRepository.class},
                new MyJpaRepository(em, entityClass)
        );

        userRepository.findById(1L);
    }
}
