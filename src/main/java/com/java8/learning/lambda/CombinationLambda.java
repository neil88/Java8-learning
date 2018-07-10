package com.java8.learning.lambda;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author xiaozhikun
 * @version 1.0
 * since JDK 1.8
 * @date 2018年06月28日
 **/
public class CombinationLambda {

    public static void main(String[] args) {
        List<Person> personList = Lists.newArrayList();
        personList.add(new Person("f1", "f1"));
        personList.add(new Person("f2", "f2"));
        personList.add(new Person("f2", "f22"));
        personList.add(new Person("f3", "f3"));
        personList.add(new Person("a4", "f4"));

        Person p = new Person("f1", "f1");

        //p -> p.getFirstName().startWith('f');
        Predicate<Person> isFirstNameStartWith = Person::isFirstNameStartWithF;
        Predicate<Person> isLastNameStartWithF = Person::isLastNameStartWithF;

        java.util.function.Predicate<Person> nameStartWithF = isFirstNameStartWith.negate().and(isLastNameStartWithF);
        List<Person> collect = personList.stream().filter(nameStartWithF).collect(Collectors.toList());
        collect.forEach(ps -> System.out.println(ps));

//        personList.sort(Comparator.comparing(Person::getFirstName).reversed().thenComparing(Person::getLastName));
//        personList.forEach(p -> System.out.println(p));

    }

    public static int comparePerson(Person p1, Person p2){
        if(p1.getFirstName().compareToIgnoreCase(p2.getFirstName()) > 0){
            return 1;
        } else if (p1.getFirstName().compareToIgnoreCase(p2.getFirstName()) < 0){
            return -1;
        } else {
            if (p1.getLastName().compareToIgnoreCase(p1.getLastName()) > 0){
                return 1;
            } else if (p1.getLastName().compareToIgnoreCase(p1.getLastName()) < 0){
                return -1;
            } else {
                return 0;
            }
        }
    }

    public int methodYy(int x){
        return x + 1;
    }
}
