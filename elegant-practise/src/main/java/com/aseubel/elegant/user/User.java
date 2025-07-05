package com.aseubel.elegant.user;

import com.aseubel.elegant.common.FieldDesc;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:41
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String address;
    @FieldDesc(name = "用户VIP等级类别")
    @Convert(converter = UserLevelTypeConverter.class)
    private UserLevelType userLevelType;
    @FieldDesc(name = "用户信用类别")
    @Convert(converter = UserCreditTypeConverter.class)
    private UserCreditType userCreditType;
}
