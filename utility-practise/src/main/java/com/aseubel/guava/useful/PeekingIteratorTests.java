package com.aseubel.guava.useful;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/10/5 下午1:59
 */
public class PeekingIteratorTests {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "李四", "王五");
        PeekingIterator<String> it = Iterators.peekingIterator(names.iterator());

        while (it.hasNext()) {
            String curr = it.next();
            if (it.hasNext()) {
                String next = it.peek();  // 只看下一条，不移动
                System.out.println("当前：" + curr + "，下一位：" + next);
            } else {
                System.out.println("最后一个元素：" + curr);
            }
        }
    }
}
