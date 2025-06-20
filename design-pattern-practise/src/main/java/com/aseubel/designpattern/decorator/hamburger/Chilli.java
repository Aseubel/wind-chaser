package com.aseubel.designpattern.decorator.hamburger;

/**
 * @author Aseubel
 * @date 2025/6/20 下午5:37
 */
public class Chilli extends Condiment {

    public Chilli(Hamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getName() {
        return hamburger.getName()+" 加辣椒";
    }

    @Override
    public double getPrice() {
        return hamburger.getPrice();  //辣椒是免费的哦
    }
}
