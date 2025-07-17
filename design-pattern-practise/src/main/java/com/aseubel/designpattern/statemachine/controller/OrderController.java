package com.aseubel.designpattern.statemachine.controller;

import com.aseubel.designpattern.statemachine.entity.Order;
import com.aseubel.designpattern.statemachine.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aseubel
 * @date 2025/7/17 下午12:48
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/getById")
    public Order getById(@RequestParam("id") Long id) {
        //根据id查询订单
        Order order = orderService.getById(id);
        return order;
    }

    @RequestMapping("/create")
    public String create(@RequestBody Order order) {
        //创建订单
        orderService.create(order);
        return "sucess";
    }

    @RequestMapping("/pay")
    public String pay(@RequestParam("id") Long id) {
        //对订单进行支付
        orderService.pay(id);
        return "success";
    }

    @RequestMapping("/deliver")
    public String deliver(@RequestParam("id") Long id) {
        //对订单进行发货
        orderService.deliver(id);
        return "success";
    }

    @RequestMapping("/receive")
    public String receive(@RequestParam("id") Long id) {
        //对订单进行确认收货
        orderService.receive(id);
        return "success";
    }

    @RequestMapping("/reject")
    public String reject(@RequestParam("id") Long id) {
        //对订单进行退货
        orderService.reject(id);
        return "success";
    }
}
