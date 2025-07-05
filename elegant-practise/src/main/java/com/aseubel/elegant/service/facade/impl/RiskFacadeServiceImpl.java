package com.aseubel.elegant.service.facade.impl;

import com.aseubel.elegant.service.facade.IRiskFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/7/5 下午10:06
 */
@Service
@RequiredArgsConstructor
public class RiskFacadeServiceImpl implements IRiskFacadeService {

    // 模拟 RPC 调用
    // private final Rpc<RiskService> riskServiceRpc;
    @Override
    public boolean isRickUser(Long userId) {
        // return riskServiceRpc.isRickUser(userId);

        // 这里直接模拟非风控用户了
//        return false;
        // 模拟风控用户
        return true;
    }
}
