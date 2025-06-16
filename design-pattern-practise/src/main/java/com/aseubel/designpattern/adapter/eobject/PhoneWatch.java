package com.aseubel.designpattern.adapter.eobject;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/6 下午5:33
 */
@Getter
public class PhoneWatch extends Electronics{
    public PhoneWatch(String name, int power, double voltage) {
        super(name, power, voltage);
    }
}
