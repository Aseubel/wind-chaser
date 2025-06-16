package com.aseubel.designpattern.singleton;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/6 下午6:11
 */
public class StaticSafeFactory {

    @Getter
    private static final Instance instance = new Instance();

    private StaticSafeFactory() { }

}
