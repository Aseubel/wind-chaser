package com.aseubel.elegant.pipeline.context;

import com.aseubel.elegant.pipeline.BizEnum;
import com.aseubel.elegant.pipeline.selector.FilterSelector;

/**
 * @author Aseubel
 * @date 2025/7/5 下午8:47
 */
public abstract class AbstractEventContext implements EventContext {

    private final BizEnum bizEnum;
    private final FilterSelector selector;

    public AbstractEventContext(BizEnum bizEnum, FilterSelector selector) {
        this.bizEnum = bizEnum;
        this.selector = selector;
    }

    @Override
    public BizEnum getBizCode() {
        return bizEnum;
    }

    @Override
    public FilterSelector getFilterSelector() {
        return selector;
    }

}
