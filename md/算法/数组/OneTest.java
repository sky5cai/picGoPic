package com.teemlink.km.smartanswer.authdepartment.service;

import org.junit.Test;

public class OneTest {

    @Test
    public void test1(){
        Long t1 = 1605024000000L;
        long t2 = 1573401600000L;
        long betweenDays = (t1 - t2) /  (60 * 60 * 24 * 1000);
        System.out.println(betweenDays);
    }
}
