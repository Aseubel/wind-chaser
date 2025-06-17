package com.aseubel.designpattern;

import com.aseubel.designpattern.factory.abstractf.AbstractFactoryBMW320;
import com.aseubel.designpattern.factory.abstractf.AbstractFactoryBMW523;
import com.aseubel.designpattern.factory.car.BMW;
import com.aseubel.designpattern.factory.car.BMW320;
import com.aseubel.designpattern.factory.car.BMW523;
import com.aseubel.designpattern.factory.method.FactoryBMW320;
import com.aseubel.designpattern.factory.method.FactoryBMW523;
import com.aseubel.designpattern.factory.simple.SimpleFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/17 下午11:34
 */
@SpringBootTest
public class FactoryTest {
    @Test
    public void simpleFactoryTest() {
        SimpleFactory simpleFactory = new SimpleFactory();
        BMW bmw320 = simpleFactory.createBMW(320);
        BMW bmw523 = simpleFactory.createBMW(523);
    }

    /**
     * 工厂方法模式将工厂抽象化，并定义一个创建对象的接口。
     * 每增加新产品，只需增加该产品以及对应的具体实现工厂类，由具体工厂类决定要实例化的产品是哪个，
     * 将对象的创建与实例化延迟到子类，这样工厂的设计就符合“开闭原则”了，扩展时不必去修改原来的代码。
     * 在使用时，用于只需知道产品对应的具体工厂，关注具体的创建过程，
     * 甚至不需要知道具体产品类的类名，当我们选择哪个具体工厂时，就已经决定了实际创建的产品是哪个了。
     * <p>
     * 但缺点在于，每增加一个产品都需要增加一个具体产品类和实现工厂类，使得系统中类的个数成倍增加，
     * 在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。
     */
    @Test
    public void factoryMethodTest() {
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();
        BMW320 bmw320 = factoryBMW320.createBMW();

        FactoryBMW523 factoryBMW523 = new FactoryBMW523();
        BMW523 bmw523 = factoryBMW523.createBMW();
    }

    /**
     * 抽象工厂模式主要用于创建相关对象的家族。
     * 当一个产品族中需要被设计在一起工作时，通过抽象工厂模式，能够保证客户端始终只使用同一个产品族中的对象；
     * 并且通过隔离具体类的生成，使得客户端不需要明确指定具体生成类；
     * 所有的具体工厂都实现了抽象工厂中定义的公共接口，因此只需要改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。
     * <p>
     * 但该模式的缺点在于添加新的行为时比较麻烦，如果需要添加一个新产品族对象时，需要更改接口及其下所有子类，这必然会带来很大的麻烦。
     */
    @Test
    public void abstractFactoryTest() {
        //生产宝马320系列配件
        AbstractFactoryBMW320 factoryBMW320 = new AbstractFactoryBMW320();
        factoryBMW320.createEngine();
        factoryBMW320.createAircondition();

        //生产宝马523系列配件
        AbstractFactoryBMW523 factoryBMW523 = new AbstractFactoryBMW523();
        factoryBMW523.createEngine();
        factoryBMW523.createAircondition();
    }
}
