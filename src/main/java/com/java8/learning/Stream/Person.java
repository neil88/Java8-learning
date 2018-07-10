package com.java8.learning.Stream;

import lombok.Data;

@Data
public class Person {

    Integer age;

    Sex sex;

    String name;

    public Person(Integer age, String name, Sex sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }


    public static enum Sex {
        MALE(0),

        FEMALE(1);

        private int code;

        private Sex(int code){
            this.code = code;
        }

        public static Sex parse(int code){
            switch (code){
                case 0:
                    return MALE;
                case 1:
                    return FEMALE;
                default:
                    return null;
            }
        }
    }


    public static enum ByAge {
        Child(0),

        YOUNG(1),

        SENIOR(2);

        private int code;

        private ByAge(int code){
            this.code = code;
        }


    }
}