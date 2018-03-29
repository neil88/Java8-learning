package com.java8.learning.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    public static void main(String[] args) {
        Person p1 = new Person("p1",":p1");
        Person p2 = new Person("p2",":p2");
        Person p3 = new Person("p3",":p3");
        Person p4 = new Person("p4",":p4");
        Person p5 = new Person("p4",":p4");
        List<Person> list = new ArrayList<>(5);
        list.add(p3);
        list.add(p4);
        list.add(p1);
        list.add(p2);
        list.add(p5);
        streamTest(list);
    }

    private static void streamTest(List<Person> list) {
        //        lambdaMethod();
        List<String> firstNameList = list.stream().filter(p -> "p1".equals(p.getFirstName())).map(p -> p.getFirstName()).collect(Collectors.toList());
        List<String> distinctList = list.stream().distinct().map(p -> p.getFirstName()).collect(Collectors.toList());
        System.out.println(firstNameList);
        System.out.println(distinctList);
    }

    private static void lambdaMethod(List<Person> list) {
        list.sort((p11,p22) -> p11.firstName.compareTo(p22.firstName));
        System.out.println(list);

        list.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName).reversed());
        System.out.println(list);

        List<Person> result  = new ArrayList<>(list.size());
        Predicate<Person> predicate = p0->"p1".equals(p0.getFirstName());
        Predicate<Person> predicate1 = predicate.negate();
        Predicate<Person> predicate2 = predicate.and(p0->":p1".equals(p0.getLastName()));
        for (Person p:list
             ) {
            if(predicate2.test(p)){
                result.add(p);
            }
        }
        System.out.println(result);


        Function<Integer, Integer> f = x->x+1;
        Function<Integer, Integer> g = x->x*2;
        Function<Integer, Integer> h = f.andThen(g);
    }
}