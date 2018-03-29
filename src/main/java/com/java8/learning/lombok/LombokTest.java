/**
 * @(#)LombokTest.java, 2018年03月27日.
 * <p>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/

package com.java8.learning.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @author hzxiaozhikun
 */

@Slf4j

public class LombokTest {

    public LombokTest() {
    }

    public static void main(String[] args) {
        Human.HumanBuilder builer  = new Human.HumanBuilder();
        Human build = builer.address("杭州市").age(11).name("Neil").id(1L).build();
        System.out.println(build);
    }


    protected boolean canEqual(Object other) {
        return other instanceof LombokTest;
    }

}