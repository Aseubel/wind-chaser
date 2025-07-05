package com.aseubel.elegant.user;

import com.aseubel.elegant.common.BaseEnum;

import java.util.Optional;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:43
 */
public enum UserLevelType implements BaseEnum<UserLevelType> {

    USER_NO_VIP(1, "no_vip"),
    USER_VIP_1(2, "vip1"),
    USER_VIP_2(3, "vip2"),
    USER_VIP_3(4, "vip3"),
    USER_VIP_4(5, "vip4"),
    USER_VIP_5(6, "vip5"),
    USER_VIP_6(7, "vip6"),
    ;

    UserLevelType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<UserLevelType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(UserLevelType.class, code));
    }

}
