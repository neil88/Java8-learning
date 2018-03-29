package com.java8.learning.Optional;

import java.util.Optional;

/**
 * Created by Neil on 2018/3/17.
 */
public class Optional3 {
    public static void main(String[] args) {
        Optional<Person> person = Optional.of(new Person());
        Optional<com.java8.learning.Optional.Car> car = person.map(Person::getCar);
        Optional<Insurance> insurance = car.map(Car::getInsurance);
        Optional<String> name = insurance.map(Insurance::getName);


        String nameOpt = Optional.of(new Person()).map(Person::getCar).map(Car::getInsurance).map(Insurance::getName).orElse(null);
        Integer years = Optional.of(new Person()).map(Person::getCar).map(Car::getInsurance).map(Insurance::getYears).orElse(null);
        Optional<Optional<String>> s = Optional.of(new Person()).map(Person::getCar).map(Car::getInsurance).map(Insurance::getOptName);


        System.out.println(name);
        System.out.println(nameOpt);
        System.out.println(years);
        System.out.println(s);


    }

}
