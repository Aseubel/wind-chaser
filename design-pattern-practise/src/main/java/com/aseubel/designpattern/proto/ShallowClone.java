package com.aseubel.designpattern.proto;

/**
 * @author Aseubel
 * @date 2025/6/20 下午3:54
 */
public class ShallowClone extends Prototype {
    @Override
    public Prototype clone(){
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }

    @Override
    public void show(){
        System.out.println("浅拷贝");
    }
}
