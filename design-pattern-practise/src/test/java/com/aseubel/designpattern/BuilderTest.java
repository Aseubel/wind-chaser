package com.aseubel.designpattern;

import com.aseubel.designpattern.builder.KFCWaiter;
import com.aseubel.designpattern.builder.Meal;
import com.aseubel.designpattern.builder.MealA;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/19 下午1:21
 */
@SpringBootTest
public class BuilderTest {
    @Test
    public void mealBuilderTest() {
        //服务员
        KFCWaiter waiter = new KFCWaiter();
        //套餐A
        MealA a = new MealA();
        //服务员准备套餐A
        waiter.setMealBuilder(a);
        //获得套餐
        Meal mealA = waiter.construct();

        System.out.print("套餐A的组成部分:");
        System.out.println(mealA.getFood()+"---"+mealA.getDrink());
    }
}
