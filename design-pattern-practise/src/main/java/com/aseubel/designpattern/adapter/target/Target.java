package com.aseubel.designpattern.adapter.target;

import com.aseubel.designpattern.adapter.eobject.Electronics;

/**
 * @author Aseubel
 * @date 2025/6/6 下午5:03
 */
public interface Target {

    /**
     * 充电
     * @param electronics 电子产品
     */
    void charge(Electronics electronics);
}
