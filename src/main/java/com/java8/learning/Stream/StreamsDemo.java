package com.java8.learning.Stream;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.*;

/**
 * Class java8-Stream接口
 *
 * @author xzk
 */
public class StreamsDemo {

    public static void main(String[] args) {

        /**
         * java.util.Stream 表示能应用在一组元素上一次执行的操作序列。
         * Stream 操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，
         * 而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。Stream 的
         * 创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set，
         * Map不支持。Stream的操作可以串行执行或者并行执行。
         */
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
//        filter(stringCollection);

//        sorted(stringCollection);
//        map(stringCollection);
//        match(stringCollection);
//        count(stringCollection);
//        reduce(stringCollection);
//        intStream(stringCollection);
//        collect();
//        groupingBy();
//        partitioningBy();

        //iterate 给定一个seed,然后递归调用unary函数
        long takes1 = System.currentTimeMillis();
        long takes2;
        Integer sum = Stream.iterate(1, unaryOperator(1)).limit(1000).parallel().collect(Collectors.summingInt(Integer::valueOf));
        System.out.println("sum:" + sum + ", takes:" + ((takes2 = System.currentTimeMillis()) - takes1));

        takes1 = takes2;
        OptionalLong sum2 = LongStream.rangeClosed(1, 1000).parallel().reduce((a, b) -> a + b);
        System.out.println("sum2:" + sum2.orElse(0) + ", takes:" + ((takes2 = System.currentTimeMillis()) - takes1));

    }

    public static UnaryOperator<Integer> unaryOperator(int operator){
        //unary函数 T ->对输入进行处理后 -> 返回T类型的结果
        return i -> i + operator;
    }

    private static void partitioningBy() {
        //分区 partitioningBy
        Person p1 =  new Person(9, "p1", Person.Sex.MALE);
        Person p2 =  new Person(20, "p2", Person.Sex.FEMALE);
        Person p3 =  new Person(60, "p3", Person.Sex.MALE);
        Person p4 =  new Person(20, "p4", Person.Sex.FEMALE);
        Person p5 =  new Person(25, "p2", Person.Sex.MALE);
        Person p6 =  new Person(28, "p4", Person.Sex.FEMALE);
        List<Person> personList = Lists.newArrayList();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);

        Map<Boolean, List<Person>> collect1 = personList.stream().
                collect(Collectors.partitioningBy(ps -> ps.getSex() == Person.Sex.MALE ? true : false));
        collect1.forEach((k, v) -> System.out.println("key:" + k + ", value:" + v));
        System.out.println("-----------------------------------------------");

        Map<Boolean, Map<Boolean, List<Person>>> collect2 = personList.stream().
                collect(Collectors.partitioningBy(ps -> ps.getSex() == Person.Sex.MALE ? true : false
                        , Collectors.partitioningBy(ps -> ps.getAge().compareTo(10) < 0 ? true : false)));
        collect2.forEach((k, v) -> System.out.println("key:" + k + ", value:" + v));
        System.out.println("-----------------------------------------------");
    }

    /**
     * groupingBy 分组
     */
    private static void groupingBy() {
        //分组 groupBy
        Person p1 =  new Person(9, "p1", Person.Sex.MALE);
        Person p2 =  new Person(20, "p2", Person.Sex.FEMALE);
        Person p3 =  new Person(60, "p3", Person.Sex.MALE);
        Person p4 =  new Person(20, "p4", Person.Sex.FEMALE);
        Person p5 =  new Person(25, "p2", Person.Sex.MALE);
        Person p6 =  new Person(28, "p4", Person.Sex.FEMALE);
        List<Person> personList = Lists.newArrayList();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);

        Map<Person.Sex, List<Person>> collect1 = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        collect1.forEach((k,v) -> System.out.println("key:" + k + ", " + v));
        System.out.println("---------------------------------------");


        Function<Person, Person.ByAge> byAgeFunction = p -> {
            if (p.getAge().compareTo(10) < 0) {
                return Person.ByAge.Child;
            } else if (p.getAge().compareTo(10) >= 0 && p.getAge().compareTo(50) < 0) {
                return Person.ByAge.YOUNG;
            } else {
                return Person.ByAge.SENIOR;
            }
        };

        //分 1级
        Map<Person.ByAge, List<Person>> collect2 = personList.stream().collect(Collectors.groupingBy(byAgeFunction));
        collect2.forEach((k,v) -> System.out.println("key:" + k + ", " + v));
        System.out.println("---------------------------------------");

        //分 2级
        Map<Person.Sex, Map<Person.ByAge, List<Person>>> collect3 = personList.stream().
                collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(byAgeFunction)));
        collect3.forEach((k,v) -> System.out.println("key:" + k + ", " + v));
        System.out.println("---------------------------------------");

        //分 3级
        Map<Person.Sex, Map<Person.ByAge, Map<String, List<Person>>>> collect4 = personList.stream().
                collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(byAgeFunction, Collectors.groupingBy(Person::getName))));
        collect4.forEach((k,v) -> System.out.println("key:" + k + ", " + v));
        System.out.println("---------------------------------------");
    }

    /**
     * 规约处理
     */
    private static void collect() {
        //collect方式 规约
        String[] arr = new String[]{"H","Hi","Hello"};

        //初始值 获取int的Function reduceFunction
        Integer sum = Arrays.stream(arr).collect(Collectors.reducing(0, String::length, Integer::sum));

        //拼接字符串
        Optional<String> splice = Arrays.stream(arr).collect(Collectors.reducing((a, b) -> a + b));
        System.out.println(sum);
        System.out.println("splice:" + splice.orElse(""));
    }

    private static void intStream(List<String> stringCollection) {
        //IntStream DoubleStream LongStream
        IntStream intStream = stringCollection.stream().mapToInt(s -> s.length());
        DoubleStream doubleStream = stringCollection.stream().mapToDouble(s -> s.length() * 1.0d);
        LongStream longStream = stringCollection.stream().mapToLong(s -> s.length() * 1L);

//        System.out.println(intStream.sum());
        System.out.println(doubleStream.sum());
        System.out.println(longStream.sum());


    }

    private static void reduce(List<String> stringCollection) {
        /**
         * 这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规约后的结果是通过Optional接口表示的
         */
        System.out.println("--------------reducing----------------");
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
    }

    private static void count(List<String> stringCollection) {
        /**
         * 计数是一个最终操作，返回Stream中元素的个数，返回值类型是long。
         */
        System.out.println("--------------counting----------------");
        long startsWithB = stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();
        System.out.println(startsWithB);    // 3
    }

    private static void match(List<String> stringCollection) {
        /**
         * Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。
         * 所有的匹配操作都是最终操作，并返回一个boolean类型的值。
         */
        System.out.println("--------------matching----------------");
        boolean anyStartsWithA = stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA = stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ = stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);      // true
    }

    private static void map(List<String> stringCollection) {
        /**
         * 中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象
         */

        String[] arr = {"hello", "world", "neil"};

        Stream<String> stringStream = Arrays.stream(arr).flatMap(str -> Stream.of(str));
        Optional<String> reduce = stringStream.reduce((s1, s2) -> s1 + "," + s2);
        System.out.println("reduce:" + reduce.orElse(""));

        List<String> collect = Arrays.stream(arr).
                flatMap(str -> Stream.of(str.split(""))).collect(Collectors.toList());
        System.out.println(collect.size() + ":" + collect);


       /* List<String[]> collect = Arrays.stream(arr).map(word -> word.split("")).collect(Collectors.toList());
        for (String[] sArr:collect
             ) {
                System.out.println(Arrays.asList(sArr));
            System.out.println("--------------------------");
        }
        Function<String, Object> stringObjectConverter = (String word) -> word.length();

        List<String> collect1 = Arrays.stream(arr).map((String word) -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println(collect1);*/

    }

    private static void sorted(List<String> stringCollection) {
        /**
         * sorted 排序.
         * 是一个中间操作，返回的是排序好后的Stream。如果你不指定一个自定义的Comparator则会使用默认排序。
         */
        System.out.println("--------------sorting-----------------");
        stringCollection
                .stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println(stringCollection);//原数据源不会被改变
    }

    private static void filter(List<String> stringCollection) {
        /**
         * Filter 过滤.
         * 过滤通过一个predicate接口来过滤并只保留符合条件的元素，该操作属于中间操作，
         * 所以我们可以在过滤后的结果来应用其他Stream操作（比如forEach）。forEach
         * 需要一个函数来对过滤后的元素依次执行。forEach是一个最终操作，所以我们不
         * 能在forEach之后来执行其他Stream操作。
         */
        System.out.println("--------------filtering---------------");
        List<String> a = stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
//                .forEach(System.out::println)
                .collect(Collectors.toList());
        System.out.println(a);
    }

}
