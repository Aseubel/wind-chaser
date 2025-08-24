package com.aseubel.mybatis.dao;

import com.aseubel.mybatis.dto.QueryCondition;

import java.io.Serializable;
import java.util.List;

/**
 * 对外暴露的接口
 * @author Aseubel
 * @date 2025/8/20 下午8:40
 */
public interface BaseDao<P extends Serializable, T> {
    List<T> selectListByQuery(QueryCondition condition);
}
