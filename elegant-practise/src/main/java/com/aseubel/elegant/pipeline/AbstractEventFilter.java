package com.aseubel.elegant.pipeline;

import com.aseubel.elegant.pipeline.context.EventContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:31
 */
@Slf4j
public abstract class AbstractEventFilter<T extends EventContext> implements EventFilter<T> {

    @Override
    public void doFilter(T context, EventFilterChain<T> chain) {
        if (context.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
            handle(context);
        }
        if (context.continueChain()) {
            chain.fireNext(context);
        }
    }

    /**
     * 子类实现的具体 handle 处理判断逻辑
     * @param context 上下文对象
     */
    protected abstract void handle(T context);

}
