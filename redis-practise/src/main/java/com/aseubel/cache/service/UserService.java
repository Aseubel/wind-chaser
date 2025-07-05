package com.aseubel.cache.service;

import com.aseubel.cache.entity.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author Aseubel
 * @date 2025/7/5 下午3:21
 */
public class UserService {

    @Cacheable(value = "user")
    public User getUser(int id) {
        return new User(id, "Aseubel", 25);
    }
}
