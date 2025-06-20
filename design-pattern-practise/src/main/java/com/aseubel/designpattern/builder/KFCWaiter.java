package com.aseubel.designpattern.builder;

import lombok.Setter;

/**
 * @author Aseubel
 * @date 2025/6/19 下午1:21
 */
@Setter
public class KFCWaiter {
    private MealBuilder mealBuilder;

    public Meal construct(){
        //准备食物
        mealBuilder.buildFood();
        //准备饮料
        mealBuilder.buildDrink();

        //准备完毕，返回一个完整的套餐给客户
        return mealBuilder.getMeal();
    }
}
