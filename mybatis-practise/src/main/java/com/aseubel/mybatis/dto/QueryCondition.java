package com.aseubel.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 综合查询条件类
 *
 * @author Aseubel
 * @date 2025/8/20 下午8:17
 */
// 查询条件主类
@Data
public class QueryCondition {
    private List<String> selectFieldList;
    private List<QueryField> queryFieldList;
    private List<OrderField> orderFieldList;
    private int pageNum = 1;
    private int pageSize = 10;
}