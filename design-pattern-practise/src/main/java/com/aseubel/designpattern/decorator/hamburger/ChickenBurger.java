package com.aseubel.designpattern.decorator.hamburger;

/**
 * @author Aseubel
 * @date 2025/6/20 下午5:28
 */
public class ChickenBurger extends Hamburger{
    public ChickenBurger(){
        name = "鸡腿堡";
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
