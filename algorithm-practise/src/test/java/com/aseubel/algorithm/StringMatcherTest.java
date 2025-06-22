package com.aseubel.algorithm;

import com.aseubel.algorithm.strmatch.*;
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
        System.out.println(BFMatcher.indexOf("This is a simple example", "example"));
    }

    @Test
    public void RKMatcherTest() {
        List<Integer> indexes = RKMatcher.indexOf("This is a simple example", "ple");
        System.out.println(indexes);
    }

    @Test
    public void KMPMatcherTest() {
        List<Integer> indexes = KMPMatcher.kmpSearch("This is a simple example", "ple");
        System.out.println(indexes);
    }

    @Test
    public void BMMatcherTest() {
        List<Integer> indexes = BMMatcher.boyerMooreSearch("This is a simple example", "ple");
        System.out.println(indexes);
    }

    @Test
    public void SundayMatcherTest() {
        List<Integer> indexes = SundayMatcher.sundaySearch("This is a simple example", "ple");
        System.out.println(indexes);
    }
}
