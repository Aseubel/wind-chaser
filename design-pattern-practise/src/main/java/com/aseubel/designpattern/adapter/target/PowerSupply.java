package com.aseubel.designpattern.adapter.target;

/**
 * @author Aseubel
 * @date 2025/6/6 下午5:06
 */
public class PowerSupply {

    public void highVoltageSupply() {
        System.out.println("High voltage supply");
    }

    public void mediumVoltageSupply() {
        System.out.println("Medium voltage supply");
    }

    public void lowVoltageSupply() {
        System.out.println("Low voltage supply");
    }
}
