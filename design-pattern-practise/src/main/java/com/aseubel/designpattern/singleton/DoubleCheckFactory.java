package com.aseubel.designpattern.singleton;

/**
 * @author Aseubel
 * @date 2025/6/6 下午4:52
 */
public class DoubleCheckFactory {

    private DoubleCheckFactory() {}

    private static volatile Instance instance = null;

    public static Instance getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckFactory.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}
