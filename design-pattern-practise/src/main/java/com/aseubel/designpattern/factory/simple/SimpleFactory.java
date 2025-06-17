package com.aseubel.designpattern.factory.simple;

import com.aseubel.designpattern.factory.car.BMW;
import com.aseubel.designpattern.factory.car.BMW320;
import com.aseubel.designpattern.factory.car.BMW523;

/**
 * @author Aseubel
 * @date 2025/6/17 下午11:29
 * @description 缺点在于不符合“开闭原则”，每次添加新产品就需要修改工厂类。
 * 在产品类型较多时，有可能造成工厂逻辑过于复杂，不利于系统的扩展维护，
 * 并且工厂类集中了所有产品创建逻辑，一旦不能正常工作，整个系统都要受到影响。
 */
public class SimpleFactory {
    public BMW createBMW(int type) {
        return switch (type) {
            case 320 -> new BMW320();
            case 523 -> new BMW523();
            default -> null;
        };
    }
}
