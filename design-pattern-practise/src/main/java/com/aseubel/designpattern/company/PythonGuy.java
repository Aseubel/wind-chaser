package com.aseubel.designpattern.company;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Aseubel
 * @date 2025/6/18 下午5:54
 * @description python开发者
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PythonGuy extends AbstractDeveloper{
    public PythonGuy(String name, Major major, Integer age) {
        super(name, major, age);
    }

    @Override
    public void showTime() {
        System.out.println("I am a Python guy!");
    }
}
