package com.aseubel.designpattern.statemachine;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:37
 */

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态机持久化配置
 * 提供两种持久化方式：内存存储（单机）和 Redis存储（分布式）
 *
 * @param <S> 状态类型
 * @param <E> 事件类型
 * @param <T> 业务上下文类型
 */
@Configuration
@Slf4j
public class Persist<S, E, T> {

    /**
     * 创建基于内存的状态机持久化器（适用于单机环境）
     *
     * @return StateMachinePersister 状态机持久化实例
     */
    @Bean(name = "stateMachineMemPersister")
    public StateMachinePersister<S, E, T> getPersister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<>() {
            // 使用HashMap存储状态机上下文
            private Map<T, StateMachineContext<S, E>> map = new HashMap<>();

            /**
             * 持久化状态机上下文
             *
             * @param context    状态机上下文
             * @param contextObj 业务上下文对象（如订单ID）
             */
            @Override
            public void write(StateMachineContext context, Object contextObj) throws Exception {
                log.info("持久化状态机, context:{}, contextObj:{}",
                        JSON.toJSONString(context),
                        JSON.toJSONString(contextObj));
                map.put((T) contextObj, context);
            }

            /**
             * 读取状态机上下文
             *
             * @param contextObj 业务上下文对象（如订单ID）
             * @return 状态机上下文
             */
            @Override
            public StateMachineContext<S, E> read(Object contextObj) throws Exception {
                log.info("获取状态机, contextObj:{}", JSON.toJSONString(contextObj));
                StateMachineContext<S, E> stateMachineContext = map.get(contextObj);
                log.info("获取状态机结果, stateMachineContext:{}",
                        JSON.toJSONString(stateMachineContext));
                return stateMachineContext;
            }
        });
    }
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 创建基于Redis的状态机持久化器（适用于分布式环境）
     *
     * @return StateMachinePersister 状态机持久化实例
     */
    @Bean(name = "stateMachineRedisPersister")
    public RedisStateMachinePersister<E, S> getRedisPersister() {
        // 创建Redis存储仓库
        RedisStateMachineContextRepository<E, S> repository = 
            new RedisStateMachineContextRepository<>(redisConnectionFactory);
        // 创建基于仓库的持久化策略
        RepositoryStateMachinePersist<E, S> persistStrategy = 
            new RepositoryStateMachinePersist<>(repository);
        return new RedisStateMachinePersister<>(persistStrategy);
    }
}
