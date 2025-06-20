package com.aseubel.designpattern.proto;

import java.util.ArrayList;

/**
 * @author Aseubel
 * @date 2025/6/20 下午3:54
 */
public class DeepClone extends Prototype {
    @SuppressWarnings("unchecked")
    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        prototype.list = new ArrayList<>(this.list);
        return prototype;
    }

    @Override
    public void show(){
        System.out.println("深拷贝");
    }
}
