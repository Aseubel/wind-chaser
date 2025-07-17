package com.aseubel.designpattern.statemachine.aspect;

import com.aseubel.designpattern.statemachine.OrderEvent;
import com.aseubel.designpattern.statemachine.OrderStatus;
import com.aseubel.designpattern.statemachine.entity.Order;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Aseubel
 * @date 2025/7/17 下午1:48
 */
@Component
@Aspect
@Slf4j
public class DemoEventListenerResultAspect {

      @Resource
      private StateMachine<OrderStatus, OrderEvent> orderStateMachine;

      @Around("@annotation(com.aseubel.designpattern.statemachine.aspect.DemoEventListenerResult)")
      public Object logResultAround(ProceedingJoinPoint joinPoint) throws Throwable {
            //获取参数
            Object[] args = joinPoint.getArgs();
            log.info("参数args:{}", args);
            Message message = (Message) args[0];
            Order order = (Order) message.getHeaders().get("order");
            //获取方法
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            // 获取DemoEventListenerResult注解
            DemoEventListenerResult demoEventListenerResult = method.getAnnotation(DemoEventListenerResult.class);
            String key = demoEventListenerResult.key();
            Object returnVal;
            try {
                  //执行方法
                  log.info("执行代理方法");
                  returnVal = joinPoint.proceed();
                  //如果业务执行正常，则保存信息
                  //成功 则为1
                  log.info("代理方法执行完毕。保存ExtendedState状态为正常。");
                  orderStateMachine.getExtendedState().getVariables().put(key + order.getId(), 1);
            } catch (Throwable e) {
                  log.error("e:{}", e.getMessage());
                  //如果业务执行异常，则保存信息
                  //将异常信息变量信息中，失败则为0
                  orderStateMachine.getExtendedState().getVariables().put(key + order.getId(), 0);
                  throw e;
            }
            return returnVal;
      }
}
