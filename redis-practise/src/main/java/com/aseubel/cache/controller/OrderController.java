package com.aseubel.cache.controller;

import com.aseubel.cache.entity.Order;
import com.aseubel.cache.service.IOrderService;
import com.aseubel.cache.util.RedissonDelayQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aseubel
 * @date 2025/8/9 上午10:00
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final RedissonDelayQueue redissonDelayQueue;
    private final IOrderService OrderService;

    @GetMapping("/get/{key}")
    public Order get(@PathVariable("key") Integer key) {
        return OrderService.getOrder(key);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order Order) {
        return OrderService.updateOrder(Order);
    }

    @DeleteMapping("/delete/{key}")
    public String delete(@PathVariable("key") Integer key) {
        OrderService.deleteOrder(key);
        return "success";
    }

    @DeleteMapping("/all/delete")
    public String deleteAll() {
        OrderService.deleteAllOrders();
        return "success";
    }

    @PostMapping("/task/delay")
    public String addTask(@RequestParam String task, @RequestParam long delay) {
        redissonDelayQueue.addTask(task, delay);
        return "success";
    }
}
