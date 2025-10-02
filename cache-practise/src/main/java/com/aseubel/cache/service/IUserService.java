package com.aseubel.cache.service;

import com.aseubel.cache.annotation.SpelResolver;
import com.aseubel.cache.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author Aseubel
 * @date 2025/8/7 下午8:27
 */
public interface IUserService {
    @Cacheable(value = "user", key = "#id")
    User getUser(int id);

    @CachePut(value = "user", key = "#updatedUser.id")
    User updateUser(User updatedUser);

    @CacheEvict(value = "user", key = "#id")
    void deleteUser(int id);

    @CacheEvict(value = "user", allEntries = true)
    void deleteAllUsers();

    @SpelResolver(expression = "#user.id")
    void processUser(User user);

    @SpelResolver(expression = "#count > 10 ? 'High' : 'Low'")
    void checkCount(int count);

    @SpelResolver(expression = "'User:' + #name + ', Role:' + #role")
    void assignRole(String name, String role);
}
