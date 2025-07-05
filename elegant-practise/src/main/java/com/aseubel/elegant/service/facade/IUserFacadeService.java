package com.aseubel.elegant.service.facade;

import com.aseubel.elegant.user.User;

import java.util.Optional;

/**
 * @author Aseubel
 * @date 2025/7/5 下午10:05
 */
public interface IUserFacadeService {

    Optional<User> findUserInfoById(Long userId);

}
