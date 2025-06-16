package com.aseubel.designpattern.adapter.eobject;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/6 下午5:21
 */
@Getter
public abstract class Electronics {

    private String name; // 电子产品名称

    private int power; // 当前电量

    private double voltage; // 电压

    public Electronics(String name, int power, double voltage) {
        this.name = name;
        this.power = power;
        this.voltage = voltage;
    }
}
