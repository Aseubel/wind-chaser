package com.aseubel.elegant.service.impl;

import com.aseubel.elegant.order.OrderContext;
import com.aseubel.elegant.order.OrderModel;
import com.aseubel.elegant.order.OrderRequest;
import com.aseubel.elegant.pipeline.BizEnum;
import com.aseubel.elegant.pipeline.EventFilter;
import com.aseubel.elegant.pipeline.FilterChainPipeline;
import com.aseubel.elegant.selector.OrderFilterSelectorFactory;
import com.aseubel.elegant.service.IOrderModelHandler;
import com.aseubel.elegant.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:52
 */
@SuppressWarnings("unchecked")
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final FilterChainPipeline<EventFilter<OrderContext>> orderPipeline;
    // 通过 selectorFactory 依靠配置文件动态生成 selector 选择器
    private final OrderFilterSelectorFactory envBasedFilterSelectorFactory;
    private final PluginRegistry<IOrderModelHandler, OrderModel> pluginRegistry;

    @Override
    public void handleOrder(OrderRequest orderRequest) {
        OrderContext orderContext = new OrderContext(BizEnum.ORDER_EVENT,
                envBasedFilterSelectorFactory.getFilterSelector(orderRequest));
        orderContext.setOrderRequest(orderRequest);
        // 通用业务逻辑处理
        try {
            orderPipeline.getFilterChain().handle(orderContext);
        } catch (Exception e) {
            log.error("下单失败", e);
            return ;
        }
        // 业务扩展点实现
        OrderModel orderModel = orderContext.getOrderModel();
        pluginRegistry.getPluginsFor(orderModel)
                .forEach(handler -> handler.handleOrderModel(orderModel));

    }
}
