package com.aseubel.guava.basic;

import static com.google.common.base.Throwables.throwIfInstanceOf;
import static com.google.common.base.Throwables.throwIfUnchecked;

/**
 * @author Aseubel
 * @date 2025/10/3 下午2:30
 */
public class ThrowableTests {
    public static void main(String[] args) {
        Foo[] foos = new Foo[2];
        Throwable failure = null;
        for (Foo foo : foos) {
            try {
                foo.bar();
            } catch (RuntimeException | Error t) {
                failure = t;
            }
        }
        if (failure != null) {
            throwIfInstanceOf(failure, IllegalArgumentException.class);
            throwIfUnchecked(failure);
            throw new AssertionError(failure);
        }
    }
}
