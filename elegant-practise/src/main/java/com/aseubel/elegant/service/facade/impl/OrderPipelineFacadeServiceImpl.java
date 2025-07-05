package com.aseubel.elegant.service.facade.impl;

import com.aseubel.elegant.service.facade.IOrderPipelineFacadeService;
import com.aseubel.elegant.service.facade.IRiskFacadeService;
import com.aseubel.elegant.service.facade.IUserFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/7/5 下午10:06
 */
@Service
@RequiredArgsConstructor
public class OrderPipelineFacadeServiceImpl implements IOrderPipelineFacadeService {

    private final IRiskFacadeService riskFacadeService;
    private final IUserFacadeService userFacadeService;

    @Override
    public IRiskFacadeService getRiskFacadeService() {
        return riskFacadeService;
    }

    @Override
    public IUserFacadeService getUserFacadeService() {
        return userFacadeService;
    }
}
