package com.aseubel.designpattern.company;

import lombok.Getter;

/**
 * @author Aseubel
 * @date 2025/6/18 下午5:30
 */
public class Company {

    @Getter
    private static final CompanyRecruit recruit = new CompanyRecruit();

    private Company() {}
}
