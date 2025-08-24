package com.aseubel.mybatis.util;

import com.aseubel.mybatis.dto.*;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Aseubel
 * @date 2025/8/20 下午8:19
 */
public class QueryConditionBuilder<T> {
    private final QueryCondition condition = new QueryCondition();

    public static <T> QueryConditionBuilder<T> builder() {
        return new QueryConditionBuilder<>();
    }

    @SafeVarargs
    public final QueryConditionBuilder<T> select(SFunction<T, ?>... columns) {
        condition.setSelectFieldList(Arrays.stream(columns)
                .map(LambdaUtils::getColumnName)
                .collect(Collectors.toList()));
        return this;
    }

    public QueryConditionBuilder<T> eq(SFunction<T, ?> column, Object val) {
        addField(column, val, QueryOperator.EQ, Logic.AND);
        return this;
    }

    public QueryConditionBuilder<T> like(SFunction<T, ?> column, Object val) {
        addField(column, val, QueryOperator.LIKE, Logic.AND);
        return this;
    }

    public QueryConditionBuilder<T> in(SFunction<T, ?> column, Collection<?> val) {
        addField(column, val, QueryOperator.IN, Logic.AND);
        return this;
    }

    public QueryConditionBuilder<T> orderByDesc(SFunction<T, ?> column) {
        if (condition.getOrderFieldList() == null) {
            condition.setOrderFieldList(new ArrayList<>());
        }
        condition.getOrderFieldList().add(new OrderField(LambdaUtils.getColumnName(column), false));
        return this;
    }

    public QueryConditionBuilder<T> pageNum(int pageNum) {
        condition.setPageNum(pageNum);
        return this;
    }

    public QueryConditionBuilder<T> pageSize(int pageSize) {
        condition.setPageSize(pageSize);
        return this;
    }

    public QueryCondition build() {
        return condition;
    }

    private void addField(SFunction<T, ?> column, Object val, QueryOperator op, Logic logic) {
        if (condition.getQueryFieldList() == null) {
            condition.setQueryFieldList(new ArrayList<>());
        }
        condition.getQueryFieldList().add(
                new QueryField(LambdaUtils.getColumnName(column), val, op, logic)
        );
    }
}
