package com.java8.learning.Optional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Class java8-Optional类:解决空引用的问题
 *
 * @author hua xu
 * @version 1.0.0
 * @date 16/08/31
 */
public class Optional2 {
    static class Outer {
        Nested nested = new Nested();

        public Nested getNested() {
            return nested;
        }
    }

    static class Nested {
        Inner inner = new Inner();

        public Inner getInner() {
            return inner;
        }
    }

    static class Inner {
        String foo = "boo";

        public String getFoo() {
            return foo;
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private static void test3() {
        Outer outer = new Outer();
        resolve(() -> outer.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    private static void test2() {
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    private static void test1() {
        Optional<Outer> optional = Optional.of(new Outer());
        optional.flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }
}
