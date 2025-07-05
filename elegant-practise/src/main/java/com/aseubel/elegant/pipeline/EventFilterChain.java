package com.aseubel.elegant.pipeline;

import com.aseubel.elegant.pipeline.context.EventContext;

/**
 * @author Aseubel
 * @date 2025/7/5 下午8:49
 */
public interface EventFilterChain<T extends EventContext> {

    /**
     * 进行事件处理逻辑
     * @param context
     */
    void handle(T context);

    /**
     * 下一个事件过滤器
     * @param context
     */
    void fireNext(T context);

}
