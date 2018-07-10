package com.java8.learning.lambda;

import java.util.Objects;

public class Person extends Human {
    public String firstName;
    public String lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String cc) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void add(String firstName) {

    }


    public static Person generate(String firstName, String lastName) {
        return new Person("A", "B");
    }

    public static Person generate2() {
        return new Person();
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isFirstNameStartWithF(){
        return getFirstName().startsWith("f");
    }
    public boolean isLastNameStartWithF(){
        return getLastName().startsWith("f");
    }

    public boolean isFirstNameStartWith(String prefix){
        Objects.requireNonNull(prefix);
        return getFirstName().startsWith(prefix);
    }


}