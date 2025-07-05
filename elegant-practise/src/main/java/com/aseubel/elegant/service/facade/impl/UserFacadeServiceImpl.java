package com.aseubel.elegant.service.facade.impl;

import com.aseubel.elegant.service.facade.IUserFacadeService;
import com.aseubel.elegant.user.User;
import com.aseubel.elegant.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Aseubel
 * @date 2025/7/5 下午10:06
 */
@Service
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements IUserFacadeService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserInfoById(Long userId) {
        return userRepository.findById(userId);
    }

}
