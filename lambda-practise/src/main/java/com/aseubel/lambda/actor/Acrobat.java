package com.aseubel.lambda.actor;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/18 下午4:04
 */
@Getter
public class Acrobat extends AbstractActor {
    public Acrobat(String name, String role) {
        super(name, role);
    }
    @Override
    public void act() {
        System.out.println("I am an acrobat! I can perform magic tricks!");
    }
}
