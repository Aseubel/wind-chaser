package com.aseubel.lambda;

/**
 * @author Aseubel
 * @date 2025/6/18 下午4:05
 */
public class Starter {
    public static void main(String[] args) {
        Actor actor = () -> System.out.println("I'm on stage!");
        actor.act();
        Actor comedian = new Comedian("HappyMan", "Comedian");
        comedian.act();
        Actor acrobat = new Acrobat("Joker", "Acrobat");
        acrobat.act();
    }
}
