package com.aseubel.cache.service;

import com.aseubel.cache.annotation.SpelResolver;
import com.aseubel.cache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/7/5 下午3:21
 */
@Service
public class UserService implements IUserService{

    private static final User user = new User(1, "Aseubel", 25);

    @Autowired
    private CacheManager cacheManager;

    /**
     * 使用@Cacheable注解的方法，当调用该方法时，会先从缓存中查找是否存在key为"user"的缓存数据，
     * 如果存在，直接返回缓存数据；如果不存在，则执行方法逻辑并将结果存入缓存中。
     * 这里假设所有的用户ID都为1，为了简化示例。
     */
    @Override
    @Cacheable(value = "user", key = "#id")
    public User getUser(int id) {
        return new User(id, "Aseubel", 25);
    }

    /**
     * 使用@CachePut注解的方法，当调用该方法时，会执行方法逻辑并将结果存入缓存中。
     * 这里假设我们可以通过ID更新用户信息，但实际上示例中我们只返回一个固定的用户。
     */
    @Override
    @CachePut(value = "user", key = "#updatedUser.id")
    public User updateUser(User updatedUser) {
        // 在实际应用中，这里可能会有更新数据库的逻辑
        return updatedUser;
    }

    /**
     * 使用@CacheEvict注解的方法，当调用该方法时，会从缓存中移除key为"user"且key值为#id的数据。
     */
    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(int id) {
        // 在实际应用中，这里可能会有删除数据库记录的逻辑
    }

    /**
     * 使用@CacheEvict注解全部删除缓存的方法，当调用该方法时，会清空"user"缓存中的所有数据。
     */
    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void deleteAllUsers() {
        // 在实际应用中，这里可能会有删除所有数据库记录的逻辑
    }

    /**
     * 示例1：从对象参数中获取属性
     */
    @Override
    @SpelResolver(expression = "#user.id")
    public void processUser(User user) {
        System.out.println("Executing processUser for user: " + user.getName());
    }

    /**
     * 示例2：对基本类型参数进行操作
     */
    @Override
    @SpelResolver(expression = "#count > 10 ? 'High' : 'Low'")
    public void checkCount(int count) {
        System.out.println("Executing checkCount with count: " + count);
    }

    /**
     * 示例3：组合多个参数
     */
    @Override
    @SpelResolver(expression = "'User:' + #name + ', Role:' + #role")
    public void assignRole(String name, String role) {
        System.out.println("Executing assignRole for " + name);
    }

}
