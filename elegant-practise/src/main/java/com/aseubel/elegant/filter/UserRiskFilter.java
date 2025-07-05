package com.aseubel.elegant.filter;

import com.aseubel.elegant.order.OrderContext;
import com.aseubel.elegant.pipeline.AbstractEventFilter;
import com.aseubel.elegant.service.facade.IOrderPipelineFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:49
 */
@Slf4j
@RequiredArgsConstructor
public class UserRiskFilter extends AbstractEventFilter<OrderContext> {

    private final IOrderPipelineFacadeService orderPipelineFacadeService;
    @Override
    protected void handle(OrderContext context) {
        if (orderPipelineFacadeService.getRiskFacadeService()
                .isRickUser(context.getOrderRequest().getUserId())) {
            log.info("业务={}, 风控用户 userId={}，拒绝下单", context.getBizCode(), context.getOrderRequest().getUserId());
            throw new RuntimeException("风控用户，拒绝下单");
        }
    }


}
