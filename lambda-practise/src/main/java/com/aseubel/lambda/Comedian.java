package com.aseubel.lambda;

/**
 * @author Aseubel
 * @date 2025/6/18 下午4:03
 */
public class Comedian extends AbstractActor {

    public Comedian(String name, String role) {
        super(name, role);
    }

    @Override
    public void act() {
        System.out.println("I am a comedian!");
    }
}
