package com.java8.learning.lambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by Neil on 2018/3/19.
 */
public class Test {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));
        List<Apple> result = filter(inventory, (Apple a) -> "red".equals(a.getColor()));
        Comparator<String> c = (s1, s2) -> s1.compareToIgnoreCase(s2);
        ApplePredicate ap = a -> a.color == null;
        Comparator<Person> byName = Comparator.comparing(p -> p.getFirstName());
    }


    public static class Apple{
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate a){
        Objects.requireNonNull(inventory);
        List<Apple> result = new ArrayList<>(inventory.size());
        for (Apple apple: inventory
             ) {
            if(a.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    interface ApplePredicate{
        public boolean test(Apple a);
    }
}
