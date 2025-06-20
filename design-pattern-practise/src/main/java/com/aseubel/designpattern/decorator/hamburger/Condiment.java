package com.aseubel.designpattern.decorator.hamburger;

/**
 * @author Aseubel
 * @date 2025/6/20 下午5:29
 */
public abstract class Condiment extends Hamburger {

    public abstract String getName();

    Hamburger hamburger;

    public Condiment(Hamburger hamburger){
        this.hamburger = hamburger;
    }
}
