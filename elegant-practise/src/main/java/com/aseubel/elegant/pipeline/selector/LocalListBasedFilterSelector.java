package com.aseubel.elegant.pipeline.selector;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Objects;

/**
 * @author Aseubel
 * @date 2025/7/5 下午9:32
 */
public class LocalListBasedFilterSelector implements FilterSelector {

    private List<String> filterNames = Lists.newArrayList();

    public LocalListBasedFilterSelector() {
    }

    public LocalListBasedFilterSelector(List<String> filterNames) {
        this.filterNames = filterNames;
    }

    @Override
    public boolean matchFilter(String currentFilterName) {
        return filterNames.stream().anyMatch(s -> Objects.equals(s, currentFilterName));
    }

    @Override
    public List<String> getFilterNames() {
        return filterNames;
    }

    public void addFilter(String clsName) {
        filterNames.add(clsName);
    }

    public void addFilterAll(List<String> filterNameList) {
        filterNames.addAll(filterNameList);
    }

}
