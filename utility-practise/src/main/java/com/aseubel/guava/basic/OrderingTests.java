package com.aseubel.guava.basic;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/10/3 下午1:01
 */
public class OrderingTests {
    public static void main(String[] args) {
        // 排序
        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> sortedNumbers = Ordering.natural().sortedCopy(numbers);
        System.out.println("Original numbers: " + numbers);
        System.out.println("Sorted numbers: " + sortedNumbers);
        // 直接扩展 Ordering 抽象类
        Ordering<String> byLengthOrdering = new Ordering<>() {
            public int compare(String left, String right) {
                return Integer.compare(left.length(), right.length());
            }
        };
        List<String> words = List.of("apple", "banana", "cherry", "date", "elderberry");
        List<String> sortedWords = byLengthOrdering.sortedCopy(words);
        System.out.println("自定义排序: " + sortedWords);
        // 链式调用
        Function<Foo, String> sortKeyFunction = new Function<Foo, String>() {
            public String apply(Foo input) {
                return input.sortedBy;
            }
        };
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(sortKeyFunction);
        // 获取第K大元素
        Foo foo1 = new Foo("apple", 1);
        Foo foo2 = new Foo("banana", 2);
        Foo foo3 = new Foo("cherry", 3);
        Foo foo4 = new Foo("date", 4);
        Foo foo5 = new Foo("elderberry", 5);
        List<Foo> foos = List.of(foo1, foo2, foo3, foo4, foo5);
        List<Foo> kthFoo = ordering.greatestOf(foos, 2); // 该方法底层调用leastOf，而leastOf底层先排序
        System.out.println("排序后: " + foos);
        System.out.println("第K大元素: " + kthFoo);
        System.out.println("最小值: " + ordering.min(foos));
        System.out.println("最大值: " + ordering.max(foos));
        System.out.println("是否有序: " + ordering.isOrdered(foos));
    }

}
