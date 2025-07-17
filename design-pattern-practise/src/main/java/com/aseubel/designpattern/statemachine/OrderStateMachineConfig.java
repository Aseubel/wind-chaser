package com.aseubel.designpattern.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:30
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    /**
     * 配置状态机状态
     *
     * @param states 状态机状态配置器
     * @throws Exception 异常
     */
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions
                // 支付事件：待支付 -> 待发货
                .withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderEvent.PAYED)
                .and()
                // 发货事件：待发货 -> 待收货
                .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderEvent.DELIVERED)
                .and()
                // 收货事件：待收货 -> 已完成
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderEvent.RECEIVED)
                .and()
                // 拒收事件：待收货 -> 待发货
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.WAIT_DELIVER).event(OrderEvent.REJECTED);
    }
}
