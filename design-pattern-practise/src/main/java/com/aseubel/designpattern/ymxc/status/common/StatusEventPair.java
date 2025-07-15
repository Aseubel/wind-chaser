package com.aseubel.designpattern.ymxc.status.common;

import java.util.Objects;

/**
 * 状态事件对，指定的状态只能接受指定的事件
 */
public class StatusEventPair<S extends BaseStatus, E extends BaseEvent> {
    /**
     * 指定的状态
     */
    private final S status;
    /**
     * 可接受的事件
     */
    private final E event;

    public StatusEventPair(S status, E event) {
        this.status = status;
        this.event = event;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StatusEventPair other) {
            return this.status.equals(other.status) && this.event.equals(other.event);
        }
        return false;
    }

    @Override
    public int hashCode() {
        // 这里使用的是google的guava包。com.google.common.base.Objects
        return Objects.hash(status, event);
    }
}