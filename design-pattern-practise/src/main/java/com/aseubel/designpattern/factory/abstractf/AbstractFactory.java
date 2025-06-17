package com.aseubel.designpattern.factory.abstractf;

/**
 * @author Aseubel
 * @date 2025/6/17 下午11:58
 */
public interface AbstractFactory {
    //制造发动机
    Engine createEngine();
    //制造空调
    Aircondition createAircondition();
}
