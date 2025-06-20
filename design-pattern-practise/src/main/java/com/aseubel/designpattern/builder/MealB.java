package com.aseubel.designpattern.builder;

/**
 * @author Aseubel
 * @date 2025/6/19 下午1:21
 */
public class MealB extends MealBuilder{

    public void buildDrink() {
        meal.setDrink("一杯柠檬果汁");
    }

    public void buildFood() {
        meal.setFood("三个鸡翅");
    }

}
