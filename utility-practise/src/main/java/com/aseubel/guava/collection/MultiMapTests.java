package com.aseubel.guava.collection;

import com.aseubel.guava.MyEnum;
import com.google.common.collect.*;
import org.apache.commons.collections4.MultiMap;
import org.jspecify.annotations.Nullable;

/**
 * @author Aseubel
 * @date 2025/10/3 下午3:16
 */
public class MultiMapTests {
    public static void main(String[] args) {
        // 你很少直接使用 Multimap 接口；
        // 更常见的是使用 ListMultimap 或 SetMultimap，它们将键映射到一个 List 或一个 Set 。

        // creates a ListMultimap with tree keys and array list values
        Multimap<String, Integer> treeListMultimap =
                MultimapBuilder.treeKeys().arrayListValues().build();
        treeListMultimap.put("a", 1);
        treeListMultimap.put("a", 2);
        treeListMultimap.put("b", 3);
        treeListMultimap.put("b", 4);
        treeListMultimap.put("c", 5);
        System.out.println(treeListMultimap);
        System.out.println(treeListMultimap.get("a"));

        // creates a SetMultimap with hash keys and enum set values
        SetMultimap<Integer, MyEnum> hashEnumMultimap =
                MultimapBuilder.hashKeys().enumSetValues(MyEnum.class).build();
    }
}
