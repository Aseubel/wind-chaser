package com.aseubel.lambda;

import com.aseubel.lambda.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Aseubel
 * @date 2025/6/22 下午1:09
 */
@SpringBootTest
public class StreamTest {

    @Test
    public void sortTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> sortedList = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9);
        list.stream().sorted().forEach(System.out::println);
        assertEquals(sortedList, list.stream().sorted().toList());
    }

    @Test
    public void ruleSortTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> sortedList = Arrays.asList(9, 6, 5, 5, 5, 4, 3, 3, 2, 1, 1);
        list.stream().sorted((a, b) -> b - a).forEach(System.out::println);
        assertEquals(sortedList, list.stream().sorted((a, b) -> b - a).toList());
    }

    @Test
    public void distinctTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> distinctList = Arrays.asList(3, 1, 4, 5, 9, 2, 6);
        assertEquals(distinctList, list.stream().distinct().toList());
    }

    @Test
    public void limitTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> limitList = Arrays.asList(3, 1, 4, 1, 5, 9, 2);
        assertEquals(limitList, list.stream().limit(7).toList());
    }

    @Test
    public void skipTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> skipList = Arrays.asList(5, 9, 2, 6, 5, 3, 5);
        assertEquals(skipList, list.stream().skip(4).toList());
    }

    @Test
    public void mapTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> mapList = Arrays.asList(9, 1, 16, 1, 25, 81, 4, 36, 25, 9, 25);
        assertEquals(mapList, list.stream().map(i -> i * i).toList());
    }

    @Test
    public void filterTest() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> filterList = Arrays.asList(4, 2, 6);
        assertEquals(filterList, list.stream().filter(i -> i % 2 == 0).toList());
    }

    @Test
    public void flatMapTest() {
        List<String> list = Arrays.asList("hello", "world", "java", "spring", "boot", "world");
        List<String> flatMapList = Arrays.asList("h", "e", "l", "l", "o", "w", "o", "r", "l", "d", "j", "a", "v", "a", "s", "p", "r", "i", "n", "g", "b", "o", "o", "t", "w", "o", "r", "l", "d");
        assertEquals(flatMapList, new ArrayList<>(flatMapList));
    }

    @Test
    public void flatMapTest2() {
        List<List<String>> list = Arrays.asList(Arrays.asList("hello", "world"), Arrays.asList("java", "spring", "boot"), Arrays.asList("world"));
        List<String> flatMapList = Arrays.asList("hello", "world", "java", "spring", "boot", "world");
        assertEquals(flatMapList, list.stream().flatMap(List::stream).toList());
    }

    @Test
    public void testGroupByAgeAndSortByName() {
        // 创建一些 Person 对象
        List<Person> people = Arrays.asList(
                new Person(30, "Alice"),
                new Person(25, "Bob"),
                new Person(30, "Charlie"),
                new Person(25, "David"),
                new Person(20, "Eve"),
                new Person(25, "Frank"),
                new Person(30, "Grace"),
                new Person(25, "Damian"),
                new Person(20, "Emma"),
                new Person(25, "Fiona")
        );

        // 原本按 name 首字母分组的二维 List
        Map<Character, List<Person>> groupedByName = people.stream()
                .collect(Collectors.groupingBy(person -> person.getName().charAt(0)));

        // 打印原本按 name 首字母分组的结果（可选）
        groupedByName.forEach((key, value) -> {
            System.out.println("Group by name starting with '" + key + "': " + value);
        });

        // 将原本按 name 首字母分组的二维 List 按 age 分组后按 name 排序
        Map<Integer, List<Person>> groupedByAgeAndSortedByName = groupedByName.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList())
                        )
                ));

        // 打印按 age 分组后按 name 排序的结果（可选）
        groupedByAgeAndSortedByName.forEach((key, value) -> {
            System.out.println("Group by age " + key + ": " + value);
        });

        // 预期结果
        Map<Integer, List<Person>> expected = new HashMap<>();
        expected.put(20, Arrays.asList(new Person(20, "Emma"), new Person(20, "Eve")));
        expected.put(25, Arrays.asList(new Person(25, "Bob"), new Person(25, "Damian"), new Person(25, "David"), new Person(25, "Frank"), new Person(25, "Fiona")));
        expected.put(30, Arrays.asList(new Person(30, "Alice"), new Person(30, "Charlie"), new Person(30, "Grace")));

        // 断言结果
        assertEquals(expected, groupedByAgeAndSortedByName);
    }
}
