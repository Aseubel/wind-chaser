package com.aseubel.designpattern.ymxc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String orderId;
    private String customerId;
    private String productId;
    private int quantity;
    private double price;
    private LocalDateTime orderDate;
}
