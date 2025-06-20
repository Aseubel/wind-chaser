package com.aseubel.designpattern.decorator;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:22
 */
public class DecoratorZero extends Decorator{
    public DecoratorZero(Human human) {
        super(human);
    }

    public void goHome() {
        System.out.println("回家里");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goHome();
    }
}
