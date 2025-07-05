package com.aseubel.elegant.pipeline;

/**
 * @author Aseubel
 * @date 2025/7/5 下午8:50
 */
@SuppressWarnings("all")
public class FilterChainPipeline<T extends EventFilter> {

    private DefaultFilterChain last;

    public FilterChainPipeline() {
    }

    public DefaultFilterChain getFilterChain() {
        return this.last;
    }

    public FilterChainPipeline addFirst(T filter) {
        DefaultFilterChain newChain = new DefaultFilterChain<>(this.last, filter);
        this.last = newChain;
        return this;
    }

    // todo 可扩展节点描述，供序列化反序列化使用
    public FilterChainPipeline addFirst(T filter, String desc) {
        DefaultFilterChain newChain = new DefaultFilterChain<>(this.last, filter);
        this.last = newChain;
        return this;
    }

}
