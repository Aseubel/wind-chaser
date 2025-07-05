package com.aseubel.elegant.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:42
 */
@Converter
public class UserCreditTypeConverter implements AttributeConverter<UserCreditType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserCreditType userCreditType) {
        return userCreditType.getCode();
    }

    @Override
    public UserCreditType convertToEntityAttribute(Integer code) {
        return UserCreditType.of(code).orElse(null);
    }
}
