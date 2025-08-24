package com.aseubel.mybatis.dao.impl;

import com.aseubel.mybatis.dao.BaseDao;
import com.aseubel.mybatis.dto.QueryCondition;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Collection;
import java.util.List;

// 抽象实现
public abstract class AbstractBaseDaoImpl<T> extends ServiceImpl<BaseMapper<T>, T>
    implements BaseDao<Long, T> {

    @Override
    public List<T> selectListByQuery(QueryCondition condition) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();

        // 构建查询条件
        if (condition.getQueryFieldList() != null) {
            condition.getQueryFieldList().forEach(f -> {
                switch (f.getOperator()) {
                    case EQ -> wrapper.eq(getColumn(f.getFieldName()), f.getValue());
                    case LIKE -> wrapper.like(getColumn(f.getFieldName()), f.getValue());
                    case IN -> wrapper.in(getColumn(f.getFieldName()), (Collection<?>) f.getValue());
                    default -> {}
                }
            });
        }

        // 排序
        if (condition.getOrderFieldList() != null) {
            condition.getOrderFieldList().forEach(o -> {
                if (o.isAsc()) {
                    wrapper.orderByAsc(getColumn(o.getFieldName()));
                } else {
                    wrapper.orderByDesc(getColumn(o.getFieldName()));
                }
            });
        }

        // 分页
        Page<T> page = new Page<>(condition.getPageNum(), condition.getPageSize());

        // 选择字段
        if (condition.getSelectFieldList() != null) {
            wrapper.select(condition.getSelectFieldList().toArray(new String[0]));
        }

        return this.list(page, wrapper);
    }

    private SFunction<T, ?> getColumn(String fieldName) {
        return new SFunction<T, Object>() {
            @Override
            public Object apply(T t) {
                throw new UnsupportedOperationException("仅用于 Lambda 表达式解析");
            }
            @Override
            public SerializedLambda writeReplace() throws Throwable {
                return new SerializedLambda(
                    this.getClass().getClassLoader(),
                    this.getClass().getName().replace('.', '/'),
                    "apply",
                    "()Ljava/lang/Object;",
                    0,
                    this.getClass().getName().replace('.', '/'),
                    fieldName
                );
            }
        };
    }
}