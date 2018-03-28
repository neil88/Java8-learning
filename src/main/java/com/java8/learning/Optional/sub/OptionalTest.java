package com.java8.learning.Optional.sub;

import java.util.Optional;

/**
 * Created by Neil on 2018/3/29.
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<Person> person = Optional.of(new Person());
//        person = person.get().getCar() == null ? Optional.empty() : person;

        Optional<Car> car = person.flatMap(p->p.getCar() == null ? Optional.empty() : p.getCar() );
//        car = car.orElse(new Car()).getInsurance() == null ? Optional.empty() : car;

        Optional<Insurance> insurance = car.flatMap(c -> c.getInsurance()==null ? Optional.empty() : c.getInsurance());
//        insurance = insurance.orElse(new Insurance()).getName() == null ? Optional.empty() : insurance;

        Optional<String> nameOpt = insurance.map(Insurance::getName);

        System.out.println(nameOpt.orElse("null"));


    }
}
