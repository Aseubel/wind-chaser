package com.aseubel.designpattern.decorator;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:21
 */
public class Decorator implements Human{

    private Human human;

    public Decorator(Human human) {
        this.human = human;
    }

    @Override
    public void wearClothes() {
        human.wearClothes();
    }
}
