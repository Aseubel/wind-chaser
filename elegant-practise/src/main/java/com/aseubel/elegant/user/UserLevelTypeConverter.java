package com.aseubel.elegant.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:43
 */
@Converter
public class UserLevelTypeConverter implements AttributeConverter<UserLevelType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserLevelType userLevelType) {
        return userLevelType.getCode();
    }

    @Override
    public UserLevelType convertToEntityAttribute(Integer code) {
        return UserLevelType.of(code).orElse(null);
    }
}
