package com.aseubel.elegant.order;

import com.aseubel.elegant.pipeline.BizEnum;
import com.aseubel.elegant.pipeline.context.AbstractEventContext;
import com.aseubel.elegant.pipeline.selector.FilterSelector;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:39
 */
@Getter
@Setter
public class OrderContext extends AbstractEventContext {

    /**
     * ================// 请求 //====================
     */
    private OrderRequest orderRequest;
    /**
     * ================// 编程模型 //====================
     */
    private OrderModel orderModel;

    public OrderContext(BizEnum bizEnum, FilterSelector selector) {
        super(bizEnum, selector);
        orderModel = new OrderModel();
    }

    @Override
    public boolean continueChain() {
        return true;
    }
}
