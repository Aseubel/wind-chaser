package com.aseubel.designpattern.decorator.hamburger;

/**
 * @author Aseubel
 * @date 2025/6/20 下午5:27
 */
public abstract class Hamburger {

    protected  String name ;

    public String getName(){
        return name;
    }
    public abstract double getPrice();
}
