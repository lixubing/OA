package com.edu.oa;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tests {
    @Test
    public void test1(){
        String a = "q";
        a.concat("b");
        System.out.println(a);
    }
    @Test
    public void test2(){
        String a = "20210107";
        String b = "20210108";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format1 = format.format(date);
        System.out.println(format1);
        System.out.println(a.compareTo(format1));
        System.out.println(b.compareTo(format1));
    }
}
