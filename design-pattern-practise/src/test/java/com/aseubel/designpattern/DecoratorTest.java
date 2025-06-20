package com.aseubel.designpattern;

import com.aseubel.designpattern.decorator.*;
import com.aseubel.designpattern.decorator.hamburger.ChickenBurger;
import com.aseubel.designpattern.decorator.hamburger.Chilli;
import com.aseubel.designpattern.decorator.hamburger.Hamburger;
import com.aseubel.designpattern.decorator.hamburger.Lettuce;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:24
 */
@SpringBootTest
public class DecoratorTest {
    @Test
    public void testWearClothes() {
        Human person = new Person();
        Decorator decorator = new DecoratorTwo(new DecoratorFirst(new DecoratorZero(person)));
        decorator.wearClothes();
    }
    
    @Test
    public void testHamburger() {
        Hamburger hamburger = new ChickenBurger();
        System.out.println(hamburger.getName()+"  价钱："+hamburger.getPrice());
        Lettuce lettuce = new Lettuce(hamburger);
        System.out.println(lettuce.getName()+"  价钱："+lettuce.getPrice());
        Chilli chilli = new Chilli(hamburger);
        System.out.println(chilli.getName()+"  价钱："+chilli.getPrice());
        Chilli chilli2 = new Chilli(lettuce);
        System.out.println(chilli2.getName()+"  价钱："+chilli2.getPrice());
    }
}
