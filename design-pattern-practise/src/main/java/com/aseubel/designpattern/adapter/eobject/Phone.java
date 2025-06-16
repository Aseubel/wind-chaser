package com.aseubel.designpattern.adapter.eobject;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/6 下午5:21
 */
@Getter
public class Phone extends Electronics {
    public Phone(String name, int power, double voltage) {
        super(name, power, voltage);
    }
}
