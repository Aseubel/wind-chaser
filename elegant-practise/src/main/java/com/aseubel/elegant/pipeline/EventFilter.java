package com.aseubel.elegant.pipeline;

import com.aseubel.elegant.pipeline.context.EventContext;

/**
 * @author Aseubel
 * @date 2025/7/5 下午8:49
 */
public interface EventFilter<T extends EventContext> {

    /**
     * 过滤逻辑封装点
     * @param context 上层透传的上下文对象
     * @param chain 责任链负责事件处理与开启下一个鉴权
     */
    void doFilter(T context, EventFilterChain<T> chain);

}
