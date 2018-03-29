package com.java8.learning.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hzxiaozhikun
 */
@Builder
@Data
public class Human {
    private String name;
    private Integer age;
    private Long id;
    private String address;
}