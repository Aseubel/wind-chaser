package com.aseubel.designpattern.builder;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/19 下午1:20
 */
@Getter
public abstract class MealBuilder {
    Meal meal = new Meal();

    public abstract void buildFood();

    public abstract void buildDrink();

}
