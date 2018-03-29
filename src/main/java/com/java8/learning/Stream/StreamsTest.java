package com.java8.learning.Stream;

import com.java8.learning.lombok.Human;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamsTest {
    public static void main(String[] args) {
        List<Animal> list = new ArrayList<>();
        list.add(new Cat("c1", 10d, "miaomiao"));
        list.add(new Cat("c2", 18d, "miaomiao"));
        list.add(new Cat("c3", 20d, "miaomiao"));
        list.add(new Cat("c4", 10d, "miaomiao"));

        List<Animal> result = null;
        filter2(list, result);
        System.out.println(result);
    }

    public static void filter(List<? extends Animal> list){
        Objects.requireNonNull(list);
        List<?> result = list.stream().distinct().filter(t -> {System.out.println("Animal:[" + t + "] ,Weight:" + t.getWeight()); return t.getWeight() > 10d;}).collect(Collectors.toList());
        System.out.println(result);
    }

    public static void filter2(List<? extends Animal> list, List<? super Cat> result){
        Objects.requireNonNull(list);
        result = list.stream().distinct().filter(t -> {System.out.println("Animal:[" + t + "] ,Weight:" + t.getWeight()); return t.getWeight() > 10d;}).collect(Collectors.toList());
        System.out.println(result);
    }

    @Data
    public static class Animal {
        protected String name;
        protected double weight;

    }

    public static class Cat extends Animal{

        private String miaomiao;

        public Cat(String name, double weight, String miaomiao){
            this.name = name;
            this.weight = weight;
            this.miaomiao = miaomiao;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", miaomiao='" + miaomiao + '\'' +
                    '}';
        }
    }


}