package com.aseubel.designpattern.decorator;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:23
 */
public class Person implements Human{
    @Override
    public void wearClothes() {
        System.out.println("穿什么呢？");
    }
}
