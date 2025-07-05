package com.aseubel.elegant.user.repo;

import com.aseubel.elegant.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:44
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
