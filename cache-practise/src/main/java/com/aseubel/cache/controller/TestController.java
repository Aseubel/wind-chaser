package com.aseubel.cache.controller;

import com.aseubel.cache.entity.User;
import com.aseubel.cache.service.IUserService;
import com.aseubel.cache.util.RedissonDelayQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aseubel
 * @date 2025/7/2 下午7:30
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TestController {

    private final RedissonDelayQueue redissonDelayQueue;
    private final IUserService userService;

    @GetMapping("/get/{key}")
    public User get(@PathVariable("key") Integer key) {
        return userService.getUser(key);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{key}")
    public String delete(@PathVariable("key") Integer key) {
        userService.deleteUser(key);
        return "success";
    }

    @DeleteMapping("/all/delete")
    public String deleteAll() {
        userService.deleteAllUsers();
        return "success";
    }

    @PostMapping("/task/delay")
    public String addTask(@RequestParam String task, @RequestParam long delay) {
        redissonDelayQueue.addTask(task, delay);
        return "success";
    }
}
