package com.aseubel.elegant.selector;

import com.aseubel.elegant.order.OrderRequest;
import com.aseubel.elegant.pipeline.selector.FilterSelector;

/**
 * @author Aseubel
 * @description 根据业务请求获取不同的 selector 选择器，而选择器的组装可以是本地手动配置（硬编码），也可以是配置文件动态调整
 * @date 2025/7/5 下午9:46
 */
public interface OrderFilterSelectorFactory {

    /**
     * 根据 orderRequest 获取对应业务线的 selector 选择器
     * 如果有其他的业务线有不同的 selector，可以多开一个接口，用方法重载性质
     * @param orderRequest
     * @return
     */
    FilterSelector getFilterSelector(OrderRequest orderRequest);
}
