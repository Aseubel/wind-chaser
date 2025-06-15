package com.aseubel.jpa.repository;

import com.aseubel.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/13 下午8:48
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByAgeBetween(Integer minAge, Integer maxAge);

    User findUserByName(String name);
}
