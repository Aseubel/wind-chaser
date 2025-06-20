package com.aseubel.designpattern.proto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/19 下午2:09
 */
@Getter
public abstract class Prototype implements Cloneable {
    protected List<String> list = new ArrayList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void show();
}
