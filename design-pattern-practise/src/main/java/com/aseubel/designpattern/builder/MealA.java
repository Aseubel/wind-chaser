package com.aseubel.designpattern.builder;

/**
 * @author Aseubel
 * @date 2025/6/19 下午1:20
 */
public class MealA extends MealBuilder{

    public void buildDrink() {
        meal.setDrink("一杯可乐");
    }

    public void buildFood() {
        meal.setFood("一盒薯条");
    }

}
