package com.aseubel.elegant;

import com.aseubel.elegant.order.BizLineEnum;
import com.aseubel.elegant.order.OrderRequest;
import com.aseubel.elegant.service.IOrderService;
import com.aseubel.elegant.user.User;
import com.aseubel.elegant.user.UserCreditType;
import com.aseubel.elegant.user.UserLevelType;
import com.aseubel.elegant.user.repo.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/7/5 下午10:11
 */
@SpringBootTest
class OrderServiceImplTest {

    @Resource
    private UserRepository userRepository;
    @Resource
    private IOrderService orderService;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .username("test")
                .address("test")
                .userLevelType(UserLevelType.USER_VIP_1)
                .userCreditType(UserCreditType.CREDIT_NORMAL)
                .build();

        userRepository.save(user);
    }

    @Test
    void handleOrder() {
        OrderRequest orderRequest = OrderRequest.builder()
                .userId(1L)
                .businessId("1")
                .productId("1")
                .bizCode(BizLineEnum.YW1)
                .build();
        orderService.handleOrder(orderRequest);
    }
}
