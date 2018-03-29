package com.java8.learning.Optional;

import java.util.Optional;

public class Insurance {

    private String name;
    private Integer years;
    private Optional<String> optName;

    public String getName() {
        return name;
    }
    public Integer getYears() {
        return years;
    }

    public Optional<String> getOptName() {
        return optName;
    }
}
