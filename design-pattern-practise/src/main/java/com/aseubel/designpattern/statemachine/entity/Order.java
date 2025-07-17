package com.aseubel.designpattern.statemachine.entity;

import com.aseubel.designpattern.statemachine.OrderStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * @author Aseubel
 * @date 2025/7/14 下午8:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("tb_order")
public class Order {
    @Id
    private String id;
    @TableField("order_code")
    private String orderCode;
    @TableField("status")
    private Integer status;
    @TableField("name")
    private String name;
    @TableField("price")
    private Double price;
}
