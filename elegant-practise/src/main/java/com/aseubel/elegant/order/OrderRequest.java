package com.aseubel.elegant.order;

import com.aseubel.elegant.common.FieldDesc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    @FieldDesc(name = "业务编码")
    private BizLineEnum bizCode;
    @FieldDesc(name = "用户ID")
    private Long userId;
    @FieldDesc(name = "商品ID")
    private String productId;
    @FieldDesc(name = "商家ID")
    private String businessId;
}
