package com.aseubel.elegant.user;

import lombok.*;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:43
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    private Long userId;
    private String username;
    private String address;
    private UserLevelType userLevelType;
    private UserCreditType userCreditType;

}
