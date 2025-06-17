package com.aseubel.designpattern.factory.abstractf;

/**
 * @author Aseubel
 * @date 2025/6/18 上午12:00
 */
public class AbstractFactoryBMW523 implements AbstractFactory {
    @Override
    public Engine createEngine() {
        return new EngineB();
    }

    @Override
    public Aircondition createAircondition() {
        return new AirconditionB();
    }
}
