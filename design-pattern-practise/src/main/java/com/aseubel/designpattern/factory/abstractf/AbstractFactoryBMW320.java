package com.aseubel.designpattern.factory.abstractf;

/**
 * @author Aseubel
 * @date 2025/6/17 下午11:59
 */
public class AbstractFactoryBMW320 implements AbstractFactory{
    @Override
    public Engine createEngine() {
        return new EngineA();
    }
    @Override
    public Aircondition createAircondition() {
        return new AirconditionA();
    }
}
