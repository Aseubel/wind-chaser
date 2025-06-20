package com.aseubel.designpattern.decorator;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:23
 */
public class DecoratorFirst extends Decorator {

    public DecoratorFirst(Human human) {
        super(human);
    }

    public void goClothespress() {
        System.out.println("去衣柜找找看。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        goClothespress();
    }
}
