package com.aseubel.mybatis.util;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.beans.Introspector;
import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/8/20 下午8:43
 */
public final class MpLambdaUtils {

    private MpLambdaUtils() {}

    public static <T> String getColumnName(SFunction<T, ?> func) throws ClassNotFoundException {
        // 提取 Lambda 表达式元数据
        var lambdaMeta = LambdaUtils.extract(func);
        var implClassName = lambdaMeta.getInstantiatedClass().getName();
        var methodName = lambdaMeta.getImplMethodName();

        // 去掉 get/is 前缀，转成字段名
        String propertyName = methodNameToProperty(methodName);

        // 根据实体类名获取字段映射
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(
                Class.forName(implClassName.replace('/', '.'))
        );

        if (columnMap == null) {
            throw new IllegalArgumentException("实体类未注册到 MyBatis-Plus：" + implClassName);
        }

        ColumnCache column = columnMap.get(propertyName.toUpperCase());
        if (column == null) {
            throw new IllegalArgumentException("字段未映射：" + propertyName);
        }

        return column.getColumn();
    }

    private static String methodNameToProperty(String methodName) {
        if (methodName.startsWith("get")) {
            return Introspector.decapitalize(methodName.substring(3));
        } else if (methodName.startsWith("is")) {
            return Introspector.decapitalize(methodName.substring(2));
        }
        throw new IllegalArgumentException("不是标准的 getter 方法：" + methodName);
    }
}
