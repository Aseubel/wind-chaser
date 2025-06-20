package com.aseubel.designpattern.decorator.hamburger;

/**
 * @author Aseubel
 * @date 2025/6/20 下午5:32
 */
public class Lettuce extends Condiment {

    public Lettuce(Hamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getName() {
        return hamburger.getName()+" 加生菜";
    }

    @Override
    public double getPrice() {
        return hamburger.getPrice()+1.5;
    }
}
