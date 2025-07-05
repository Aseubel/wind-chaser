package com.aseubel.elegant.pipeline.selector;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:32
 */
public interface FilterSelector {

    /**
     * filter 匹配
     * @param currentFilterName
     * @return
     */
    boolean matchFilter(String currentFilterName);

    /**
     * 获取所有 filter 的名称
     * @return
     */
    List<String> getFilterNames();
}
