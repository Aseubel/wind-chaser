package com.aseubel.designpattern.decorator;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:23
 */
public class DecoratorTwo extends Decorator {

    public DecoratorTwo(Human human) {
        super(human);
    }

    public void findClothes() {
        System.out.println("找到一件D&G。。");
    }

    @Override
    public void wearClothes() {
        super.wearClothes();
        findClothes();
    }
}
