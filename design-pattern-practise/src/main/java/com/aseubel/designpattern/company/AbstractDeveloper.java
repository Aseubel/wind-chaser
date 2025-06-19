package com.aseubel.designpattern.company;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/18 下午5:46
 */
@Getter
public abstract class AbstractDeveloper implements Candidate{
    private String name;
    private Major major;
    private Integer age;

    private AbstractDeveloper() { }

    public AbstractDeveloper(String name, Major major, Integer age) {
        this.name = name;
        this.major = major;
        this.age = age;
    }
}
