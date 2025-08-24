package com.aseubel.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 排序字段
 * @author Aseubel
 * @date 2025/8/20 下午8:36
 */
@Data
@AllArgsConstructor
public class OrderField {
    private String fieldName;
    private boolean asc;
}
