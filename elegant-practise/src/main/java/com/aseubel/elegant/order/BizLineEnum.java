package com.aseubel.elegant.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:39
 */
@Getter
@AllArgsConstructor
public enum BizLineEnum {
    YW1("YW1"),
    YW2("YW2"),
    ;

    private final String code;

}
