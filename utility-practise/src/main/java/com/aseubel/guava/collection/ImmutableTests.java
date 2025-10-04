package com.aseubel.guava.collection;

import com.aseubel.guava.basic.Foo;
import com.google.common.collect.ImmutableSet;

import java.awt.*;
import java.util.Set;

/**
 * @author Aseubel
 * @date 2025/10/3 下午2:33
 */
public class ImmutableTests {

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");
    public static final ImmutableSet<Color> WEBSAFE_COLORS =
            ImmutableSet.of(
                    Color.decode("#000000"),
                    Color.decode("#0000FF"),
                    Color.decode("#00FF00"),
                    Color.decode("#FF0000"),
                    Color.decode("#FFFFFF"));
    public static final ImmutableSet<Color> GOOGLE_COLORS =
            ImmutableSet.<Color>builder()
                    .addAll(WEBSAFE_COLORS)
                    .add(new Color(0, 191, 255))
                    .build();
    public static void main(String[] args) {
        Foo foo = new Foo(COLOR_NAMES);
        System.out.println(foo.bars);
    }

    private static ImmutableSet<Color> toImmutableSet(Set<Color> colors) {
        return ImmutableSet.copyOf(colors);
    }

    static class Foo {
        final ImmutableSet<String> bars;
        Foo(Set<String> bars) {
            this.bars = ImmutableSet.copyOf(bars); // defensive copy!
        }
    }
}
