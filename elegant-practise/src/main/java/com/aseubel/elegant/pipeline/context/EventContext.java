package com.aseubel.elegant.pipeline.context;

import com.aseubel.elegant.pipeline.BizEnum;
import com.aseubel.elegant.pipeline.selector.FilterSelector;

/**
 * @author Aseubel
 * @date 2025/7/5 下午8:46
 */
public interface EventContext {
    /**
     * 获取业务编码
     * @return
     */
    BizEnum getBizCode();

    /**
     * 获取业务过滤器选择器
     * @return
     */
    FilterSelector getFilterSelector();

    /**
     * 是否继续责任链执行？
     * @return
     */
    boolean continueChain();
}
