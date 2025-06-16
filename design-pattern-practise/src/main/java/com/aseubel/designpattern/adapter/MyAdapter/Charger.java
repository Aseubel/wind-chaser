package com.aseubel.designpattern.adapter.MyAdapter;

import com.aseubel.designpattern.adapter.eobject.Electronics;
import com.aseubel.designpattern.adapter.target.PowerSupply;
import com.aseubel.designpattern.adapter.target.Target;

/**
 * @author Aseubel
 * @date 2025/6/6 下午5:08
 */
public class Charger extends PowerSupply implements Target {
    @Override
    public void charge(Electronics electronics) {
        double voltage = electronics.getVoltage();
        if (voltage > 36) {
            System.out.println(electronics.getName() + ": High voltage output");
            highVoltageSupply();
        } else if (voltage > 15) {
            System.out.println(electronics.getName() + ": Medium voltage output");
            mediumVoltageSupply();
        } else if (voltage > 0) {
            System.out.println(electronics.getName() + ": Low voltage output");
            lowVoltageSupply();
        } else {
            System.out.println("Invalid voltage input");
        }
    }
}
