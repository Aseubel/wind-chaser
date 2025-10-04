package com.aseubel.guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Foo {
    @Nullable
    String sortedBy;
    int notSortedBy;
    private String aString;
    private int anInt;
    private Enum anEnum;

    public Foo(String sortedBy, int notSortedBy) {
        this.sortedBy = sortedBy;
        this.notSortedBy = notSortedBy;
    }

    public int compareTo(Foo that) {
        return ComparisonChain.start()
                .compare(this.aString, that.aString)
                .compare(this.anInt, that.anInt)
                .compare(this.anEnum, that.anEnum, Ordering.natural().nullsLast())
                .result();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
               .add("sortedBy", sortedBy)
               .add("notSortedBy", notSortedBy)
               .add("aString", aString)
               .add("anInt", anInt)
               .add("anEnum", anEnum)
               .toString();
    }

    public void bar() {

    }
}