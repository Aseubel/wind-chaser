package com.aseubel.collection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aseubel
 * @date 2025/7/2 上午10:27
 */
@SpringBootTest
public class ListTest {
    @Test
    public void arrayListTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.addAll(List.of(2, 3, 4));
        System.out.println(list);
        System.out.println(list.contains(1));
        System.out.println(list.contains(5));
        System.out.println(list.indexOf(2));
        list.removeAll(List.of(2, 3));
        System.out.println(list);
        System.out.println(list.containsAll(List.of(1, 4)));
    }
}
