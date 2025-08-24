package com.aseubel.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 查询字段条件
 * @author Aseubel
 * @date 2025/8/20 下午8:35
 */
@Data
@AllArgsConstructor
public class QueryField {
    private String fieldName;
    private Object value;
    private QueryOperator operator;
    private Logic logic;
}
