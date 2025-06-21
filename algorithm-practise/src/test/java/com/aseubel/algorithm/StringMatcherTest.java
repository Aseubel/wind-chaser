package com.aseubel.algorithm;

import com.aseubel.algorithm.strmatch.BFMatcher;
import com.aseubel.algorithm.strmatch.RKMatcher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Aseubel
 * @date 2025/6/21 下午11:05
 */
@SpringBootTest
public class StringMatcherTest {
    @Test
    public void BfMatcherTest() {
        System.out.println(BFMatcher.indexOf("hello world", "wo"));
    }

    @Test
    public void RKMatcherTest() {
        List<Integer> indexes = RKMatcher.indexOf("hello world", "wo");
        System.out.println(indexes);
    }
}
