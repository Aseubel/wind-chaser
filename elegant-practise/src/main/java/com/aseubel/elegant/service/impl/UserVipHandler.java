package com.aseubel.elegant.service.impl;

import com.aseubel.elegant.order.OrderModel;
import com.aseubel.elegant.service.IOrderModelHandler;
import com.aseubel.elegant.user.UserLevelType;
import com.aseubel.elegant.user.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:52
 */
@Slf4j
@Service
public class UserVipHandler implements IOrderModelHandler {

    @Override
    public void handleOrderModel(OrderModel orderModel) {
        log.info("用户VIP处理");
        UserModel userModel = orderModel.getUserModel();
        UserLevelType userLevelType = userModel.getUserLevelType();
        switch (userLevelType) {
            case USER_NO_VIP -> {
                log.info("普通用户处理");
            }
            case USER_VIP_1 -> {
                log.info("用户VIP1处理, 优惠10元");
            }
            case USER_VIP_2 -> {
                log.info("用户VIP2处理, 优惠20元");
            }
            case USER_VIP_3 -> {
                log.info("用户VIP3处理, 优惠30");
            }
            case USER_VIP_4 -> {
                log.info("用户VIP4处理, 优惠40");
            }
            case USER_VIP_5 -> {
                log.info("用户VIP5处理, 优惠50");
            }
            case USER_VIP_6 -> {
                log.info("用户VIP6处理, 优惠60");
            }
            default -> {
                log.info("未知用户等级");
            }
        }
    }

    @Override
    public boolean supports(OrderModel orderModel) {
        return orderModel.getUserModel().getUserLevelType() != null;
    }

}
