package com.aseubel.elegant.pipeline;

import com.aseubel.elegant.common.BaseEnum;

/**
 * @author Aseubel
 * @date 2025/7/5 下午8:47
 */
public enum BizEnum implements BaseEnum<BizEnum> {
    ORDER_EVENT(1, "下单业务"),
    METRIC_EVENT(2, "业务2"),
    SIGNAL_EVENT(3, "业务3"),

    ;

    private final Integer code;
    private final String name;

    BizEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
