package com.aseubel.guava.basic;

import com.google.common.base.Preconditions;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Aseubel
 * @date 2025/10/2 上午9:43
 */
public class PreconditionsTests {
    private static final Boolean initialized = false;

    public static void main(String[] args) {
        checkNegative(2);
        compareIntegers(10, 5);
        checkNotNull(null);
        checkState(initialized, "必须先调用initialize()方法");
    }

    private static void checkNegative(Integer numChildren) {
        try {
            checkArgument(numChildren >= 0, "numChildren (%s) cannot be negative", numChildren);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void compareIntegers(Integer i, Integer j) {
        try {
            checkArgument(i < j, "Expected i < j, but %s >= %s", i, j);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkNotNull(Object object) {
        try {
            Preconditions.checkNotNull(object, "object cannot be null");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkState(Boolean state, String message) {
        try {
            Preconditions.checkState(state, message);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 检查 index 是否是具有指定大小的列表、字符串或数组的有效 元素 索引。
     * 元素索引的范围可以从 0（包含）到 size 不包含 。
     * 你不需要直接传递列表、字符串或数组；只需传递其大小即可。
     * 返回 index。
     * @param index
     * @param size
     */
    private static void checkElementIndex(int index, int size) {
        try {
            Preconditions.checkElementIndex(index, size);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 检查 index 是否是具有指定大小的列表、字符串或数组的有效 位置 索引。
     * 元素索引的范围可以从 0（不包含）到 size 不包含 。
     * 你不需要直接传递列表、字符串或数组；只需传递其大小即可。
     * 返回 index。
     * @param index
     * @param size
     */
    private static void checkPositionIndex(int index, int size) {
        try {
            Preconditions.checkPositionIndex(index, size);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 检查 start 和 end 都在 [0, size] 范围内（并且 end 至少与 start 一样大）。附带自己的错误消息。
     * @param start
     * @param end
     * @param size
     */
    private static void checkPositionIndexes(int start, int end, int size) {
        try {
            Preconditions.checkPositionIndexes(start, end, size);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
