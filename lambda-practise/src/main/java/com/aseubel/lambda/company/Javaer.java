package com.aseubel.lambda.company;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Aseubel
 * @date 2025/6/18 下午5:49
 * @description 爪哇选手
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Javaer extends AbstractDeveloper {
    public Javaer(String name, Major major, Integer age) {
        super(name, major, age);
    }

    @Override
    public void showTime() {
        System.out.println("Javaer show time!");
    }
}
