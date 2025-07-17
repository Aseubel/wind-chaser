package com.aseubel.designpattern.statemachine;

import com.aseubel.designpattern.statemachine.aspect.DemoEventListenerResult;
import com.aseubel.designpattern.statemachine.common.CommonConstants;
import com.aseubel.designpattern.statemachine.entity.Order;
import com.aseubel.designpattern.statemachine.mapper.OrderMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Aseubel
 * @date 2025/7/17 下午1:14
 */
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
@Slf4j
public class OrderStateListenerImpl {
    @Resource
    private OrderMapper orderMapper;

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    @DemoEventListenerResult(key = CommonConstants.ORDER_PAY_TRANSITION)
    public void payTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("支付，状态机反馈信息：{}",  message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
        //模拟异常
        if(Objects.equals(order.getName(),"A")){
            throw new RuntimeException("执行业务异常");
        }
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    @DemoEventListenerResult(key = CommonConstants.ORDER_DELIVER_TRANSITION)
    public void deliverTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("发货，状态机反馈信息：{}",  message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    @DemoEventListenerResult(key = CommonConstants.ORDER_RECEIVE_TRANSITION)
    public void receiveTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("确认收货，状态机反馈信息：{}",  message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.FINISH.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }

    //驳回业务
    @OnTransition(source = "WAIT_RECEIVE", target = "WAIT_DELIVER")
    @DemoEventListenerResult(key = CommonConstants.ORDER_REJECT_TRANSITION)
    public void rejectTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("确认收货，状态机反馈信息：{}",  message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }
}
