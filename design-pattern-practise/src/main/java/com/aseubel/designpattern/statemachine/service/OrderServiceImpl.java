package com.aseubel.designpattern.statemachine.service;

import com.aseubel.designpattern.statemachine.OrderEvent;
import com.aseubel.designpattern.statemachine.OrderStatus;
import com.aseubel.designpattern.statemachine.common.CommonConstants;
import com.aseubel.designpattern.statemachine.entity.Order;
import com.aseubel.designpattern.statemachine.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:48
 */
@Service("orderService")
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    private StateMachine<OrderStatus, OrderEvent> orderStateMachine;
    @Resource
    private StateMachinePersister<OrderStatus, OrderEvent, String> stateMachineMemPersister;
    @Resource
    private StateMachinePersister<OrderStatus, OrderEvent, String> stateMachineRedisPersister;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @Override
    public Order create(Order order) {
        order.setStatus(OrderStatus.WAIT_PAYMENT.getKey());
        orderMapper.insert(order);
        return order;
    }

    /**
     * 支付订单
     *
     * @param id 订单id
     * @return 订单
     */
    @Override
    public Order pay(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},，订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderEvent.PAYED, order, CommonConstants.ORDER_PAY_TRANSITION)) {
            log.error("线程名称：{},支付失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("支付失败, 订单状态异常");
        }
        return order;
    }

    /**
     * 发货订单
     *
     * @param id
     * @return
     */
    @Override
    public Order deliver(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},尝试发货，订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderEvent.DELIVERED, order, CommonConstants.ORDER_DELIVER_TRANSITION)) {
            log.error("线程名称：{},发货失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("发货失败, 订单状态异常");
        }
        return order;
    }

    /**
     * 收货订单
     *
     * @param id
     * @return
     */
    @Override
    public Order receive(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},尝试收货，订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderEvent.RECEIVED, order, CommonConstants.ORDER_RECEIVE_TRANSITION)) {
            log.error("线程名称：{},收货失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("收货失败, 订单状态异常");
        }
        return order;
    }

    @Override
    public Order reject(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},尝试退货，订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderEvent.REJECTED, order, CommonConstants.ORDER_REJECT_TRANSITION)) {
            log.error("线程名称：{},退货失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("退货失败, 订单状态异常");
        }
        return order;
    }

    /**
     * 发送订单状态转换事件(持久化到内存)
     * synchronized修饰保证这个方法是线程安全的
     *
     * @param changeEvent 订单状态转换事件
     * @param order       订单信息
     * @param key         事件的 key
     * @return true/false
     */
    private synchronized boolean sendEvent(OrderEvent changeEvent, Order order, String key) {
        boolean result = false;
        try {
            //启动状态机
            orderStateMachine.start();
            //尝试恢复状态机状态
            stateMachineMemPersister.restore(orderStateMachine, String.valueOf(order.getId()));
            Message<OrderEvent> message = MessageBuilder.withPayload(changeEvent).setHeader("order", order).build();
            result = orderStateMachine.sendEvent(message);
            if (!result) {
                return false;
            }
            //获取到监听的结果信息
            Integer o = (Integer) orderStateMachine.getExtendedState().getVariables().get(key + order.getId());
            //操作完成之后,删除本次对应的key信息
            orderStateMachine.getExtendedState().getVariables().remove(key + order.getId());
            //如果事务执行成功，则持久化状态机
            if (Objects.equals(1, Integer.valueOf(o))) {
                //持久化状态机状态
                stateMachineMemPersister.persist(orderStateMachine, String.valueOf(order.getId()));
            } else {
                //订单执行业务异常
                return false;
            }
        } catch (Exception e) {
            log.error("订单操作失败:{}", e);
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }

    /**
     * 发送订单状态转换事件(持久化到redis)
     * synchronized修饰保证这个方法是线程安全的
     *
     * @param changeEvent 订单状态转换事件
     * @param order       订单信息
     * @param key         事件的 key
     * @return true/false
     */
//    private synchronized boolean sendEvent(OrderEvent changeEvent, Order order, String key) {
//        log.info("准备发送事件" + order);
//        boolean result = false;
//        try {
//            //启动状态机
//            orderStateMachine.start();
//            //尝试恢复状态机状态
//            stateMachineRedisPersister.restore(orderStateMachine, String.valueOf(order.getId()));
//            Message message = MessageBuilder.withPayload(changeEvent).setHeader("order", order).build();
//            log.info("发送事件" + order);
//            result = orderStateMachine.sendEvent(message);
//            if (!result) {
//                return false;
//            }
//            //获取到监听的结果信息
//            Integer o = (Integer) orderStateMachine.getExtendedState().getVariables().get(key + order.getId());
//            log.info("获取到监听的结果信息" + o);
//            //操作完成之后,删除本次对应的key信息
//            orderStateMachine.getExtendedState().getVariables().remove(key + order.getId());
//            //如果事务执行成功，则持久化状态机
//            if (Objects.equals(1, Integer.valueOf(o))) {
//                //持久化状态机状态 nb
//                stateMachineRedisPersister.persist(orderStateMachine, String.valueOf(order.getId()));
//            } else {
//                //订单执行业务异常
//                return false;
//            }
//        } catch (Exception e) {
//            log.error("订单操作失败:{}", e);
//        } finally {
//            orderStateMachine.stop();
//        }
//        return result;
//    }
}
