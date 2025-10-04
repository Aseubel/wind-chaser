package com.aseubel.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Aseubel
 * @date 2025/10/3 下午2:58
 */
public class MultisetTests {
    // count(E)	         统计已添加到这个 multiset 中的某个元素的出现次数。
    // elementSet()	     将一个 Multiset<E> 中的不同元素视为一个 Set<E>。
    // entrySet()	     与 Map.entrySet() 类似，返回一个 Set<Multiset.Entry<E>>，其中包含支持 getElement() 和 getCount() 的条目。
    // add(E, int)	     添加指定元素指定数量的出现次数。
    // remove(E, int)	 移除指定元素指定数量的出现次数。
    // setCount(E, int)	 将指定元素的出现次数设置为指定的非负值。
    // size()	         返回 Multiset 中所有元素的总出现次数。
    public static void main(String[] args) {
        Multiset<Integer> multiset = HashMultiset.create();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number (-1 to exit): ");
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (num == -1) {
                break;
            }
            multiset.add(num);
            System.out.print("Enter a number (-1 to exit): ");
        }
        System.out.println("size: " + multiset.size());
        System.out.println(multiset);
        for (Multiset.Entry<Integer> entry : multiset.entrySet()) {
            System.out.println(entry.getElement() + " : " + entry.getCount());
        }
        // iterator() 遍历每个元素的所有出现，因此迭代长度等于 multiset.size()
        for (Integer integer : multiset) {
            System.out.print(integer);
        }
    }
}
