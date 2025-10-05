package com.aseubel.guava.useful;

import com.alibaba.nacos.shaded.com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/10/5 下午12:22
 */
public class MapDifferenceTests {
    public static void main(String[] args) {
        Map<String, String> oldMap = ImmutableMap.of("a", "1", "b", "2");
        Map<String, String> newMap = ImmutableMap.of("a", "1", "b", "3", "c", "4");

        MapDifference<String, String> diff = Maps.difference(oldMap, newMap);

        System.out.println("只在旧map中有：" + diff.entriesOnlyOnLeft());
        System.out.println("只在新map中有：" + diff.entriesOnlyOnRight());
        System.out.println("相同的：" + diff.entriesInCommon());
        System.out.println("不相同的：" + diff.entriesDiffering());
    }
}
