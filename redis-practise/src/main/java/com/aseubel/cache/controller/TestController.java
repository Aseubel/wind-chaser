package com.aseubel.cache.controller;

import com.aseubel.cache.util.RedissonDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aseubel
 * @date 2025/7/2 下午7:30
 */
@RestController
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonDelayQueue redissonDelayQueue;

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @PostMapping("/task/delay")
    public String addTask(@RequestParam String task, @RequestParam long delay) {
        redissonDelayQueue.addTask(task, delay);
        return "success";
    }
}
